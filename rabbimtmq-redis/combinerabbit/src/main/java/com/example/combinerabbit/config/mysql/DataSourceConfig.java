package com.example.combinerabbit.config.mysql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Component
public class DataSourceConfig {

    /* @Bean(name = "primaryData")
     @Qualifier("primaryData")*/


    @Bean(name = "primaryData")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryData() {
        DataSource build = DataSourceBuilder.create().build();


        return build;
    }

    @Primary
    @Bean(name = "secondaryData")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondaryData() {

        DataSource build = DataSourceBuilder.create().build();
        System.out.println(build + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        return build;
    }

    @Bean(name = "datasource")
    public DynamicDataSource dataSource() {
        Map<Object, Object> map = new HashMap<>();
        System.out.println(primaryData() + "-------------------->>>>>>>>>>>>" + secondaryData());
        map.put("primary", primaryData());
        map.put("secondary", secondaryData());
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(map);
        //note 设置默认的数据库连接
        dynamicDataSource.setDefaultTargetDataSource(primaryData());

        return dynamicDataSource;
    }


    @Bean
    public PlatformTransactionManager txManager(@Qualifier("datasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
