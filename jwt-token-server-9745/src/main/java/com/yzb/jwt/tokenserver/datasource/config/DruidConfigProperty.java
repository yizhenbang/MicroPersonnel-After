package com.yzb.jwt.tokenserver.datasource.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName: DruidConfigProperty
 * Description: 所有druid的配置属性
 * date: 2021/12/11 16:10
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Data
@Component
public class DruidConfigProperty {
    @Value("${spring.personnel.datasource.driver-class-name}")
    String driverClassName; // 数据库驱动程序
    @Value("${spring.personnel.datasource.url}")
    String url; // 数据库连接地址
    @Value("${spring.personnel.datasource.username}")
    String username; // 数据库的用户名
    @Value("${spring.personnel.datasource.password}")
    String password; // 数据库的用户名
    @Value("${spring.personnel.datasource.druid.initial-size}")
    int initialSize; // 初始化连接数
    @Value("${spring.personnel.datasource.druid.min-idle}")
    int minIdle; // 最小维持连接数
    @Value("${spring.personnel.datasource.druid.max-active}")
    int maxActive; // 最大连接数
    @Value("${spring.personnel.datasource.druid.max-wait}")
    long maxWait; // 最长等待时间
    @Value("${spring.personnel.datasource.druid.time-between-eviction-runs-millis}")
    long timeBetweenEvictionRunsMillis; // 关闭空闲连接间隔
    @Value("${spring.personnel.datasource.druid.min-evictable-idle-time-millis}")
    long minEvictableIdleTimeMillis; // 最小存活时间
    @Value("${spring.personnel.datasource.druid.validation-query}")
    String validationQuery; // 验证查询
    @Value("${spring.personnel.datasource.druid.test-while-idle}")
    boolean testWhileIdle; // 测试空闲连接是否可用
    @Value("${spring.personnel.datasource.druid.test-on-borrow}")
    boolean testOnBorrow; // 测试后返回连接
    @Value("${spring.personnel.datasource.druid.test-on-return}")
    boolean testOnReturn; // 测试后归还
}
