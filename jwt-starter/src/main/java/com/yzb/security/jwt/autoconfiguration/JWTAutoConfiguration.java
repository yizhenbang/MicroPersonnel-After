package com.yzb.security.jwt.autoconfiguration;

import com.yzb.security.jwt.config.JWTConfigProperty;
import com.yzb.security.jwt.config.PasswordEncryptConfigProperties;
import com.yzb.security.jwt.service.PasswordEncryptService;
import com.yzb.security.jwt.service.TokenService;
import com.yzb.security.jwt.service.impl.DefaultPasswordEncryptServiceImpl;
import com.yzb.security.jwt.service.impl.DefaultTokenServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: JWTAutoConfiguration
 * Description: JWT的自动装配设置
 * date: 2021/12/11 12:19
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties({JWTConfigProperty.class, PasswordEncryptConfigProperties.class})
public class JWTAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public TokenService tokenService() {
        return new DefaultTokenServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncryptService passwordEncrypt() {
        return new DefaultPasswordEncryptServiceImpl();
    }
}
