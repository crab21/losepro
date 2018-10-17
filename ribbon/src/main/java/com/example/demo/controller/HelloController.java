package com.example.demo.controller;

import com.example.demo.service.HelloService;
import com.example.demo.service.HelloServiceFeign;
import com.example.demo.service.User;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Created by k on 2018/8/14.
 */
@Api(value = "whafsdkfasjdkfksdl", description = "sbbbb")
@RestController
class HelloController {

    @Autowired
    HelloService helloService;

    @ApiOperation(value = "测试hi的请求是否成功的", httpMethod = "GET", response = String.class, notes = "就看看这个是干嘛的")
    @ApiParam(name = "name", value = "wang", defaultValue = "wang")
    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) throws ExecutionException, InterruptedException {
        System.out.println(name);
        System.out.println(helloService);
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        User user = helloService.test10(name);
        User user1 = helloService.test10(name);
        User user2 = helloService.test10(name);
        User user3 = helloService.test10(name);
        User user4 = helloService.test10(name);
        User user5 = helloService.test10(name);
        System.out.println(user.getName());
        context.close();
//        System.out.println(stringFuture.get());
        return "ok";
    }

    @Autowired
    HelloServiceFeign helloServiceFeign;

    @ApiOperation(value = "测试names跨域请求是否成功的", httpMethod = "GET", response = String.class)
    @GetMapping(value = "/names")
    @RequestMapping("/names")
    public String name() {

        System.out.println("name......");
        String wang = helloServiceFeign.name();
        return wang;
    }

}
