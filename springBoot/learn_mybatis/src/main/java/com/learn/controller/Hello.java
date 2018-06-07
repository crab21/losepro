package com.learn.controller;

import com.learn.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k on 2018/6/7.
 */
@RestController
public class Hello {
    @Autowired
    UserServices userServices;

    @RequestMapping("/hello")
    public String name() {
        String i = userServices.masterUser();
        System.out.println(i + "------------" + "来自master");
        return "ok";
    }

    @GetMapping("/hi")
    public String names() {
        int i = userServices.dsUser();
        System.out.println(i + "----------" + "来在ds2");
        return "ok";
    }
}
