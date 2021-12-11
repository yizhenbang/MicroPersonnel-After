package com.yzb.security.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: JWTConfigProperty
 * Description: JWT 验证的相关属性信息
 * date: 2021/12/10 23:00
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@ConfigurationProperties(prefix = "com.yzb.security.jwt")
public class JWTConfigProperty {
    private String issuer;//签发者
    private Long expire;//过期时间
    private String reset;//加密秘钥
    private String sing;//签名
}
