package com.example.demo.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by k on 2018/8/29.
 */
@FeignClient(value = "service-hi")
public interface HelloServiceFeign {
    @RequestMapping("hi")
    String name(@RequestParam("name") String name);
}
