package com.learn.service;

/**
 * Created by k on 2018/6/7.
 */

import com.learn.learnDataSource.DS;
import com.learn.mapperMaster.UserMapper;
import com.learn.mapperSlave.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServices {
    @Autowired
    UserMapper userMapper;

    @Autowired
    StudentMapper studentMapper;

    @DS("master")
    public String masterUser() {
        int i = userMapper.selectUserInfo();
        return String.valueOf(i);
    }

    @DS("ds2")
    public int dsUser() {
        int i = studentMapper.selectUserInfo();
        return i;
    }
}
