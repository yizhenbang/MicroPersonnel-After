package com.yzb.jwt.tokenserver.datasource.druid;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * ClassName: DruidSpringConfig
 * Description: Druid监控Spring
 * date: 2021/12/11 16:03
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class DruidSpringConfig {

    @Bean("druidStatInterceptor")
    public DruidStatInterceptor getDruidStatInterceptor() {
        return new DruidStatInterceptor();
    }


    @Bean("jdkRegexpMethodPointcut")
    @Scope("prototype")
    public JdkRegexpMethodPointcut getJdkRegexpMethodPointcut() {//获取切面
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPatterns("com.yzb.database.service.*", "com.yzb.database.dao.*", "com.yzb.database.action.*");
        return jdkRegexpMethodPointcut;
    }

    @Bean("defaultPointcutAdvisor")//通过advisor 将 advice和切面进行配置
    public DefaultPointcutAdvisor getDefaultPointcutAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut jdkRegexpMethodPointcut) {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(druidStatInterceptor);
        defaultPointcutAdvisor.setPointcut(jdkRegexpMethodPointcut);
        return defaultPointcutAdvisor;
    }
}
