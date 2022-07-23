package com.D.school_trade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author: Ding
 * @date: 2022/7/17 14:04
 * @description:
 * @modify:
 */

public class TransactionManagerConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        // 创建 dataSource 数据源事务管理器
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        // 设置数据源
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
