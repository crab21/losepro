package com.example.combinerabbit.config.mysql.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@MapperScan(basePackages = "com.example.combinerabbit.mapper")
public class DbOne {
/*
    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String username;

    @Value("${master.datasource.password}")
    private String password;
    @Value("${master.datasource.type}")
    private String type;
*/

    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryData() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean(name = "mysqlNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate primaryJdbcTemplate(@Qualifier("primary") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

/*
    @Bean(name = "ssoSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("primary") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/sso/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "ssoTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("primary") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ssoSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("ssoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }*/
}
