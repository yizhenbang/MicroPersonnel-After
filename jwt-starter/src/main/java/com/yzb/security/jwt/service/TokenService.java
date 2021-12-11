package com.yzb.security.jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import javax.crypto.SecretKey;
import java.util.Map;

/**
 * ClassName: TokenService
 * Description: 生成Token数据、解析token数据、刷新Token数据
 * date: 2021/12/10 23:04
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public interface TokenService {
    /*
     * @Author ZhenBang-Yi
     * @Date 2021/12/10 23:09
     * @Description //TODO  获取Token的key
     * @Since version-1.0
     **/
    SecretKey generateKey();

    /*
     * @Author ZhenBang-Yi
     * @Date 2021/12/10 23:11
     * @Description //TODO 生成Token数据信息
     * @param id: 传入一个ID
     * @param subject: 附加信息
     * @return java.lang.String
     * @Since version-1.0
     **/
    String createToken(String id, Map<String, Object> subject) throws JsonProcessingException;

    /*
     * @Author ZhenBang-Yi
     * @Date 2021/12/11 10:14
     * @Description //TODO 验证Token
     * @param token:  需要验证的Token信息
     * @return boolean 是否有效
     * @Since version-1.0
    **/
    boolean verify(String token);

    /*
     * @Author ZhenBang-Yi
     * @Date 2021/12/11 10:14
     * @Description //TODO 刷新Token数据
     * @param token:  旧Token
     * @return java.lang.String 返回新的token
     * @Since version-1.0
    **/
    String refreshToken(String token) throws JsonProcessingException;

    /*
     * @Author ZhenBang-Yi
     * @Date 2021/12/11 10:15
     * @Description //TODO 解析Token
     * @param token: 需要解析的Token
     * @return io.jsonwebtoken.Jws<io.jsonwebtoken.Claims> 解析之后的信息数据
     * @Since version-1.0
    **/
    Jws<Claims> parseToken(String token) throws JwtException;
}
