package com.example.combinerabbit;

import com.example.combinerabbit.mapper.UserMapper;
import com.example.combinerabbit.model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CombinerabbitApplicationTests {


    @Autowired
    UserMapper userMapper;

/*    @Autowired
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
        *//*Object o = redisTemplate.opsForValue().get(jobs.getName());
        System.out.println(o + "----------------");*//*
        String s = stringRedisTemplate.opsForValue().get(jobs.getName());
        System.out.println(s);
        Jobs jobs1 = new Gson().fromJson(s, Jobs.class);
        System.out.println(jobs1.getTime());

        ListOperations listOperations = redisTemplate.opsForList();

    }

    @Test
    public void redisAdd() {
        stringRedisTemplate.opsForList().leftPush("test", "90");

    }*/

    @Test
    public void testUserMapper() {
        List<Users> allUser = userMapper.findAllUser();
        List<Users> allUser1 = userMapper.findAllUser();
        System.out.println(allUser1.size());
        System.out.println(allUser.size());
    }
}
