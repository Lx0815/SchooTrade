package com.D.school_trade.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author: Ding
 * @date: 2022/7/17 14:01
 * @description:
 * @modify:
 */

public class JdbcConfig {

    @Value("${MySQL_JDBC.driver}")
    private String driver;

    @Value("${MySQL_JDBC.url}")
    private String url;

    @Value("${MySQL_JDBC.username}")
    private String username;

    @Value("${MySQL_JDBC.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
