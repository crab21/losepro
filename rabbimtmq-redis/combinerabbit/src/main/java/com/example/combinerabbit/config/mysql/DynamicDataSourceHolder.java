package com.example.combinerabbit.config.mysql;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DynamicDataSourceHolder {
    public static final String DEFAULTS = "master";
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void putDataSource(String name) {
        System.out.println(getDataSource() + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
        threadLocal.set(name);
        System.out.println(getDataSource() + "}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
    }

    public static String getDataSource() {
        return threadLocal.get();
    }

    public static void removeDataSource() {
        threadLocal.remove();
    }
}
