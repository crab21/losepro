package com.example.combinerabbit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisControl {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/rediscon")
    public String rediscon() {
//        Long test = stringRedisTemplate.opsForList().leftPush("test", "90");
        Long test = stringRedisTemplate.opsForList().leftPush("test", "01", "90");

        System.out.println(test + "-----------");
        return "ok";
    }
}
