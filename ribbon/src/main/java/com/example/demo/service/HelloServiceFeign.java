package com.example.demo.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by k on 2018/8/29.
 */
/*@FeignClient(value = "service-hi")
public interface HelloServiceFeign {
    @RequestMapping("hi")
    String name(@RequestParam("name") String name);
}*/
@FeignClient(value = "eurka-server")
public interface HelloServiceFeign {
    @RequestMapping("/add")
    String name();
}

