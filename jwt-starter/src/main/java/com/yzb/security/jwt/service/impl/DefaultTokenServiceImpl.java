package com.yzb.security.jwt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzb.security.jwt.config.JWTConfigProperty;
import com.yzb.security.jwt.service.TokenService;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: TokenServiceImpl
 * Description: token的数据实现
 * date: 2021/12/10 23:04
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class DefaultTokenServiceImpl implements TokenService {

    @Autowired
    private JWTConfigProperty jwtConfigProperty;
    // @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.application.name?:default}")// 因为会有很多的项目引入他所以设置一个项目名称
    private String application;
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;// 设置签名的加密算法

    @Override
    public SecretKey generateKey() {
        byte[] decodeBase64 = Base64.decodeBase64(Base64.encodeBase64(this.jwtConfigProperty.getReset().getBytes()));
        return new SecretKeySpec(decodeBase64, 0, decodeBase64.length, "HES");
    }

    @Override
    public String createToken(String id, Map<String, Object> subject) throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now();//当前的时间
        LocalDateTime expireTime = now.plusSeconds(this.jwtConfigProperty.getExpire());//失效时间
        Map<String, Object> claims = new HashMap<>();//扩展数据
        claims.put("site", "com.yzb.jwt");
        claims.put("createTime", dateFormat(LocalDateTime.now()));//JDK8的LocalDateTime并不支持什么Jackson
        Map<String, Object> header = new HashMap<>();//头信息
        header.put("author", "爆可爱的小易");
        header.put("application", application);
        header.put("teacher", "爆可爱的小李");

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims)//扩展信息
                .setIssuer(this.jwtConfigProperty.getIssuer())//签发人
                .setIssuedAt(dateFormat(now))//签发时间
                .signWith(this.signatureAlgorithm, this.generateKey())//签发的key
                .setExpiration(dateFormat(expireTime))//失效时间
                .setHeader(header)//头信息
                .setId(id)
                .setSubject(this.objectMapper.writeValueAsString(subject));//附加信息

        return jwtBuilder.compact();//压缩打包
    }

    @Override
    public boolean verify(String token) {
        try {
            // 如果能够正常解析出数据，那就代表token没问题
            Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public String refreshToken(String token) throws JsonProcessingException {
        if (verify(token)){
            Claims body = this.parseToken(token).getBody();
            return this.createToken(body.getId(),this.objectMapper.readValue(body.getSubject(),Map.class));
        }
        return null;
    }

    @Override
    public Jws<Claims> parseToken(String token) throws JwtException {
        if (verify(token)){
            return Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
        }
        return null;
    }

    private Date dateFormat(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
