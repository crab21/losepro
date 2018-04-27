package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
