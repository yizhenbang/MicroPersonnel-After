package com.yzb.security.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: PasswordEncryptConfigProperties
 * Description: 密码加密的相关属性
 * date: 2021/12/11 11:29
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@ConfigurationProperties(prefix = "com.yzb.security.jwt.pwd")
public class PasswordEncryptConfigProperties {
    private int repeat;//一共循环加密多少次
    private String salt;//盐值
}
