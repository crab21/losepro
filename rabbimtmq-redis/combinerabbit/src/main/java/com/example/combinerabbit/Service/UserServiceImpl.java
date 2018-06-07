package com.example.combinerabbit.Service;

import com.example.combinerabbit.config.mysql.TargetSource;
import com.example.combinerabbit.mapper.UserMapper;
import com.example.combinerabbit.mapper.StudentMapper;
import com.example.combinerabbit.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;

    @TargetSource("master")
    public List<Users> update() {
        int allUser = userMapper.findAllUser();

        System.out.println(allUser + "---------" + "来自master");
/*        for (Users user : allUser) {
            System.out.println(user.getId() + "-------" + user.getName());
        }*/
        return null;
    }

    @TargetSource("db2")
    public void updateSlave() {
        int allUsers = studentMapper.findAllUsers();
        System.out.println(allUsers + "------------来自slave");
    }

    public void insert(int i, String name1) {
        userMapper.insert(i, name1);
    }

    public void insert(int i, String name1, boolean b) {
        userMapper.inserts(i, name1, true);
    }
}
