package com.example.combinerabbit.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
/*

    */
/* @Bean(name = "primaryData")
     @Qualifier("primaryData")*//*



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
*/

    @Primary
    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource dataSource1() {
        return new DruidDataSource();
    }

    @Bean(name = "secondary")
    @ConfigurationProperties(prefix = "spring.datasource.second") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return new DruidDataSource();
    }


    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Bean(name = "datasource")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("master", dataSource1());
        dsMap.put("ds2", dataSource2());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Bean
    public PlatformTransactionManager txManager(@Qualifier("datasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
