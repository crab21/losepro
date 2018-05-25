package com.dhlk.airsensor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("com.dhlk.airsensor.biz.dao")
public class AirsensorApplication {


    public static void main(String[] args) {
        SpringApplication.run(AirsensorApplication.class, args);
    }
}
