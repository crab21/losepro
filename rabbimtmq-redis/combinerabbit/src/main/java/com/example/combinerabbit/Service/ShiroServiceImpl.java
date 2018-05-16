package com.example.combinerabbit.Service;

import com.example.combinerabbit.mapper.ShiroMapper;
import com.example.combinerabbit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroServiceImpl {
    @Autowired
    ShiroMapper shiroMapper;

   public User findUserByUserName(String username) {
        return shiroMapper.findByUserName(username);
    }
}
