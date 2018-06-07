package com.example.combinerabbit.config.mysql;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Component
public class DataSourceConfig {

    /* @Bean(name = "primaryData")
     @Qualifier("primaryData")*/


    @Autowired
    @Qualifier(value = "primary")
    DataSource primaryData;

    @Resource
    @Qualifier(value = "secondary")
    DataSource secondaryData;


    @Bean(name = "datasource")
    public DynamicDataSource dataSource() {
        Map<Object, Object> map = new HashMap<>();
        System.out.println(primaryData + "-------------------->>>>>>>>>>>>" + secondaryData);
        map.put("primary", primaryData);
        map.put("secondary", secondaryData);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(map);
        //note 设置默认的数据库连接
        dynamicDataSource.setDefaultTargetDataSource(primaryData);

        return dynamicDataSource;
    }


    @Bean
    public PlatformTransactionManager txManager(@Qualifier("datasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
