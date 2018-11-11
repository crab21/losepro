package com.example.combinerabbit;

import com.example.combinerabbit.Service.ShiroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//不开启数据库注解
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})


//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
//@Import({DataSourceConfig.class})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@RestController
public class CombinerabbitApplication {

    @Autowired
    ShiroServiceImpl shiroService;

    public static void main(String[] args) {
        SpringApplication.run(CombinerabbitApplication.class, args);
    }

    @GetMapping("/cnm")
    public String cnm() {
//        User userByUserName = shiroService.findUserByUserName("123");
        int i = shiroService.countId();
        System.out.println(i);
//        System.out.println(userByUserName.getRoles().size());
        return "ok";
    }
}
