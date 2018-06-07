package com.learn.learnDataSource;

import org.springframework.context.annotation.Configuration;

/**
 * Created by k on 2018/6/7.
 */
@Configuration
public class DataSourceContextHolder {
    public static final String DEFAULTS = "master";
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setDb(String dbType) {
        threadLocal.set(dbType);
    }

    public static String getDb() {
        return threadLocal.get();
    }

    public static void removeDb() {
        threadLocal.remove();
    }
}
