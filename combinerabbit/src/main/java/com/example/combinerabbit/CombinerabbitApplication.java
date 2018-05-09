package com.example.combinerabbit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.combinerabbit.mapper")
public class CombinerabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CombinerabbitApplication.class, args);
	}
}
