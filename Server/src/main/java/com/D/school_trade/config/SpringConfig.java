package com.D.school_trade.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Ding
 * @date: 2022/7/16
 * @description:
 * @modify:
 */

@Configuration
@ComponentScan({"com.D.school_trade.service", "com.D.school_trade.dao"})
@PropertySource("classpath:MySQL_JDBC.properties")
@EnableTransactionManagement
@Import({JdbcConfig.class, MyBatisSpringConfig.class, TransactionManagerConfig.class})
public class SpringConfig {
}
