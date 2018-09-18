package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by k on 2018/8/14.
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCollapser(batchMethod = "hiService",
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
    public Future<String> test10(String name) {
        System.out.println("..............");
        return null;
    }

    @HystrixCommand()
    public List<String> hiService(List<String> name) {
        System.out.println(name.size() + ">>>>>>>>>>>");
        String join = StringUtils.join(name, ",");
        String[] forObject = restTemplate.getForObject("http://SERVICE-HI/hi?name={1}", String[].class, StringUtils.join(name, ","));


        return Arrays.asList(forObject);
    }


    public String hiError(List<String> name) {
        return "hi" + name + ",sorry error";
    }


}