package com.example.combinerabbit.config.mysql;

import org.springframework.stereotype.Component;

@Component
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void putDataSource(String name) {
        threadLocal.set(name);
    }

    public static String getDataSource() {
        return threadLocal.get();
    }

    public static void removeDataSource() {
        threadLocal.remove();
    }
}
