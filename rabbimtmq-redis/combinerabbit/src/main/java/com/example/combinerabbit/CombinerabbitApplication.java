package com.example.combinerabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//不开启数据库注解
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})


//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
//@Import({DataSourceConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CombinerabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombinerabbitApplication.class, args);
    }
}
