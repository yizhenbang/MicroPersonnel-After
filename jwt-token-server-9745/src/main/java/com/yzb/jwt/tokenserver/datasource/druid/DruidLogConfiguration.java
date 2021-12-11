package com.yzb.jwt.tokenserver.datasource.druid;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: DruidLogConfiguration
 * Description: Druid的日志记录，记录所有的慢sql
 * date: 2021/12/11 16:07
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class DruidLogConfiguration {
    @Bean("logFilter")
    public Slf4jLogFilter getSlf4jLogFilter() {
        Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
        slf4jLogFilter.setDataSourceLogEnabled(true);//启用数据库日志
        slf4jLogFilter.setStatementExecutableSqlLogEnable(true);//记录执行日志
        return slf4jLogFilter;
    }
}
