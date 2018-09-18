package com.example.demo.service;

import feign.hystrix.FallbackFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by k on 2018/9/16.
 */
public class HelloServiceFallback implements FallbackFactory {
    @Override
    public Object create(Throwable throwable) {
        return null;
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 10;
        AtomicInteger atomicInteger = new AtomicInteger(a);
        atomicInteger.compareAndSet(a, b);
        int i = atomicInteger.get();
        System.out.println(i);
    }

}
