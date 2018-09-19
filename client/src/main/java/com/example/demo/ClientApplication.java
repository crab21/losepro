package com.example.demo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ClientApplication {

    public static void main(String[] args) {
//        String resultsStr = getResultsStrs(User.class);
//        System.out.println(resultsStr);
        SpringApplication.run(ClientApplication.class, args);
//        String wang = new Gson().toJson("wang");
//        System.out.println(wang);
    }

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public List<User> home(@RequestParam("name") String name) {
        System.out.println("--->");
        System.out.println(name);
        List<User> objects = new ArrayList<>();
        objects.add(new User("wang", "123", "345"));
        return objects;
    }

    public List<User> hiError(String name) {
        System.out.println("sorry---->>>");
        return new ArrayList<>();
    }

    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }


    public static String getResultsStrs(Class origin) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@Results({\n");
        for (Field field :
                origin.getDeclaredFields()) {
            String name = field.getName();
            String s = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toLowerCase();
            stringBuffer.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", name, s));
        }
        stringBuffer.append("})");
        return stringBuffer.toString();
    }
}
