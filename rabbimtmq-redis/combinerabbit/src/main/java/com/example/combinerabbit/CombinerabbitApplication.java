package com.example.combinerabbit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//不开启数据库注解
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.example.combinerabbit.mapper")
@SpringBootApplication
public class CombinerabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombinerabbitApplication.class, args);
    }
}
