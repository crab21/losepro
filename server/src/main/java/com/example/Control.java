package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k on 2018/9/25.
 */
@RestController
public class Control {

    @RequestMapping("/add")
    public String add() {
        return "ok";
    }
}
