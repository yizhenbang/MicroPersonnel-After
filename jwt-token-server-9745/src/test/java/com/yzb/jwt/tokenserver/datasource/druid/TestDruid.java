package com.yzb.jwt.tokenserver.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.yzb.jwt.tokenserver.StarterTokenServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;

/**
 * ClassName: TestDruid
 * Description: 测试Deuid
 * date: 2021/12/11 15:18
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StarterTokenServer.class)
public class TestDruid {
    @Autowired
    private DruidDataSource druidDataSource;

    @Test
    public void get() throws SQLException {
        System.out.println(druidDataSource.getConnection());
    }
}
