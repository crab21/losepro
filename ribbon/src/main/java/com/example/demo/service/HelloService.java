package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by k on 2018/8/14.
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCollapser(batchMethod = "hiService",
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    public User test10(String name) {
        System.out.println("..............");
        return null;
    }

    @HystrixCommand()
    public List<User> hiService(List<String> name) {
        System.out.println(name.size() + ">>>>>>>>>>>");
        String join = StringUtils.join(name, ",");
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};

        ResponseEntity<List<User>> exchange = restTemplate.exchange("http://SERVICE-HI/hi?name={1}", HttpMethod.GET, null, responseType, StringUtils.join(name, ","));
        List<User> body = exchange.getBody();


        return body;
    }


    public String hiError(List<String> name) {
        return "hi" + name + ",sorry error";
    }


}