package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k on 2018/4/16.
 */
@RestController
public class HelloWorld {
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }

}
