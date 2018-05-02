package com.dh.springredis.Redis;

import com.dh.springredis.Redis.Jobs;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringredisApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Before
    public void setUp() {

    }

    @Test
    public void contextLoads() {
        Jobs jobs = new Jobs("ww", UUID.randomUUID() + "", 12);
        stringRedisTemplate.opsForValue().set(jobs.getName(), new Gson().toJson(jobs));
        /*Object o = redisTemplate.opsForValue().get(jobs.getName());
        System.out.println(o + "----------------");*/
        String s = stringRedisTemplate.opsForValue().get(jobs.getName());
        System.out.println(s);
        Jobs jobs1 = new Gson().fromJson(s, Jobs.class);
        System.out.println(jobs1.getTime());

    }

}
