package com.example.combinerabbit;

import com.example.combinerabbit.Service.UserServiceImpl;
import com.example.combinerabbit.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TimeTest {
    @Autowired
    UserServiceImpl userMapper;

    public static void main(String[] args) {


    }

    /**
     * 对日期的处理
     */
    public void dateLearn() {
        java.sql.Date dateSql = new java.sql.Date(System.currentTimeMillis());
        Date dateUtil = new Date();
        System.out.println(dateUtil);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss E W F a");
        String format = simpleDateFormat.format(dateUtil);
        System.out.println(format);
        System.out.println(dateSql);
    }

    @GetMapping("/datats")
    public String test() {
        List<Users> allUser = userMapper.update();
        userMapper.updateSlave();

        System.out.println(allUser.size() + "-------------");
        return "ok";
    }
}
