package com.example.combinerabbit.config.mysql.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@MapperScan(basePackages = "com.example.combinerabbit.mapper")
public class DbTwo {


    /*    @Value("${slave.datasource.url}")
        private String url;

        @Value("${slave.datasource.username}")
        private String username;

        @Value("${slave.datasource.password}")
        private String password;
        @Value("${slave.datasource.type}")
        private String type;*/
    @Bean(name = "secondary")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondaryData() {

        DruidDataSource build = new DruidDataSource();
        return build;
    }


    @Bean(name = "mysqlNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate primaryJdbcTemplate(@Qualifier("secondary") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
/*
    @Primary
    @Bean(name = "messageSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("secondary") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/message/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "messageTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("secondary") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "messageSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("messageSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }*/

}
