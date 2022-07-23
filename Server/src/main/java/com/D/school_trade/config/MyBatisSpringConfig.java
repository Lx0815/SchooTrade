package com.D.school_trade.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * @author: Ding
 * @date: 2022/7/17 13:57
 * @description:
 * @modify:
 */


public class MyBatisSpringConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        // 创建 SqlSessionFactoryBean
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置类型别名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.D.school_trade.pojo");
        // 设置数据源对象
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // 创建 MapperScannerConfigurer
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 设置 包扫描路径
        mapperScannerConfigurer.setBasePackage("com.D.school_trade.dao");
        return mapperScannerConfigurer;
    }

}
