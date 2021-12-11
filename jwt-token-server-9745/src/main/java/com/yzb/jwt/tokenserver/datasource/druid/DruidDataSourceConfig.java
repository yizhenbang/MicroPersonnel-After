package com.yzb.jwt.tokenserver.datasource.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;
import com.yzb.jwt.tokenserver.datasource.config.DruidConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DruidDataSourceConfiguration
 * Description: DruidDatasource 配置类
 * date: 2021/12/11 14:55
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class DruidDataSourceConfig {
    @Bean
    public DruidDataSource druidDataSource(
            @Autowired DruidConfigProperty druidConfigProperty,
            @Autowired StatFilter statFilter,//注入SQL监控
            @Autowired WallFilter wallFilter,//注入SQL防火墙
            @Autowired Slf4jLogFilter slf4jLogFilter
    ) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidConfigProperty.getDriverClassName()); // 数据库驱动程序
        dataSource.setUrl(druidConfigProperty.getUrl()); // 数据库的连接地址
        dataSource.setUsername(druidConfigProperty.getUsername()); // 数据库用户名
        dataSource.setPassword(druidConfigProperty.getPassword()); // 数据库密码
        dataSource.setInitialSize(druidConfigProperty.getInitialSize()); // 连接池初始化大小
        dataSource.setMinIdle(druidConfigProperty.getMinIdle()); // 最小维持的连接数量
        dataSource.setMaxActive(druidConfigProperty.getMaxActive()); // 最大的连接数量
        dataSource.setMaxWait(druidConfigProperty.getMaxWait()); // 最大等待时间
        dataSource.setTimeBetweenEvictionRunsMillis(druidConfigProperty.getTimeBetweenEvictionRunsMillis()); // 检查的间隔时间
        dataSource.setMinEvictableIdleTimeMillis(druidConfigProperty.getMinEvictableIdleTimeMillis()); // 存活时间
        dataSource.setValidationQuery(druidConfigProperty.getValidationQuery()); // 验证SQL
        dataSource.setTestWhileIdle(druidConfigProperty.isTestWhileIdle()); // 测试连接是否可用
        dataSource.setTestOnBorrow(druidConfigProperty.isTestOnBorrow()); // 获取时检测
        dataSource.setTestOnReturn(druidConfigProperty.isTestOnReturn()); // 归还时检测

        // SQL 监控
        List<Filter> filters = new ArrayList<>();//设置所有可能存在的监控项
        filters.add(statFilter);// SQL监控
        filters.add(wallFilter);// 将SQL防火墙加入
        filters.add(slf4jLogFilter);//将日志进行注入
        dataSource.setProxyFilters(filters);

        return dataSource;
    }
}
