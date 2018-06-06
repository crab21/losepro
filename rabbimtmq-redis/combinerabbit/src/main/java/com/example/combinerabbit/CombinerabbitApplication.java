package com.example.combinerabbit;

import com.example.combinerabbit.config.mysql.DataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

//不开启数据库注解
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.example.combinerabbit.mapper")
@SpringBootApplication(exclude = {})
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@Import({DataSourceConfig.class})
public class CombinerabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombinerabbitApplication.class, args);
    }
}
