package com.yzb.jwt.tokenserver.datasource.druid;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * ClassName: DruidMonitorConfig
 * Description: Druid 监控的所有配置
 * date: 2021/12/11 15:30
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class DruidMonitorConfig {
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*"
        );

        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_USERNAME, "druid");//账号
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_JMX_PASSWORD, "druid");//密码
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_ALLOW, "127.0.0.1");//白名单
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_DENY, "");//黑名单
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_RESET_ENABLE, "true");//操作数据很多允许重置

        return registrationBean;
    }

    @Bean
    @DependsOn("webStatFilter")
    public FilterRegistrationBean<WebStatFilter> registrationBean(WebStatFilter webStatFilter) {// 设置Web监控
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(webStatFilter);
        registrationBean.addUrlPatterns("/*");//监控所有
        registrationBean.addInitParameter(
                WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.jpg,*.gif,*.pdf,*.css,*.js,/druid/*"
        );//监控所有

        return registrationBean;
    }

    @Bean("webStatFilter")
    public WebStatFilter webStatFilter() {//获取web状态过滤
        WebStatFilter webStatFilter = new WebStatFilter();
        webStatFilter.setSessionStatEnable(true);//对Session状态进行监控
        return webStatFilter;
    }

    @Bean("sqlFilter")
    public StatFilter getSqlStatFilter(// SQL监控
            @Value("${spring.personnel.datasource.druid.stat.merge-sql}")
                    boolean MergeSql,
            @Value("${spring.personnel.datasource.druid.stat.log-slow-sql}")
                    boolean LogSlowSql,
            @Value("${spring.personnel.datasource.druid.stat.slow-sql-Millis}")
                    long slowSqlMillis
    ) {
        StatFilter statFilter = new StatFilter();

        statFilter.setMergeSql(MergeSql);//合并SQL
        statFilter.setLogSlowSql(LogSlowSql);//是否记录慢SQL
        statFilter.setSlowSqlMillis(slowSqlMillis);//慢SQL的时间标准

        return statFilter;
    }

    @Bean("sqlWallConfig")
    public WallConfig getWallConfig() {//SQL防火墙配置
        WallConfig wallConfig = new WallConfig();
        wallConfig.setDeleteAllow(false);//不允许进行delete操作
        return wallConfig;
    }

    @Bean("sqlWallFilter")
    @DependsOn("sqlWallConfig")
    public WallFilter getWallFilter(WallConfig wallConfig) {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }

}
