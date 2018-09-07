package com.example.demo;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class MonlearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonlearnApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/hi")
    public String hi() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new User(i, "w", "male", 12));
        }
        List<User> users = userRepository.saveAll(list);
        for (User uer :
                users) {
            System.out.println(uer.getId()+uer.getName()+uer.getAge()+uer.getSex());
        }
        return "ok";
    }
}


