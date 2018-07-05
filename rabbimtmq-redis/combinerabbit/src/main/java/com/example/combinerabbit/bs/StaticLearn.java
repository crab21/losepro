package com.example.combinerabbit.bs;

import java.util.Date;

/**
 * Created by k on 2018/7/5.
 */
public class StaticLearn {
    private static Date date = new Date();

    public static void main(String[] args) throws InterruptedException {
        /**
         * do not this
         * 日期不可变
         */
        while (true) {
            System.out.println(date);
            Thread.sleep(3000);
        }
    }
}
