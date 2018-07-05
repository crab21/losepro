package com.example.combinerabbit.bs;

import java.util.Date;

/**
 * Created by k on 2018/7/5.
 */
public class StaticLearn {
    private static Date date = new Date();

    public static StaticLearn SAVE_HOOK = null;

    public static void main(String[] args) throws InterruptedException {
        /**
         * do not this
         * 日期不可变
         */
//        while (true) {
//            System.out.println(date);
//            Thread.sleep(3000);
//        }
        SAVE_HOOK = new StaticLearn();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (null != SAVE_HOOK) { //此时对象应该处于(reachable, finalized)状态
            System.out.println("Yes , I am still alive1");
        } else {
            System.out.println("No , I am dead");
        }
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (null != SAVE_HOOK) {
            System.out.println("Yes , I am still alive2");
        } else {
            System.out.println("No , I am dead2");
        }
    }

    @Override
    protected void finalize() throws Throwable {

        super.finalize();
        System.out.println("execute method finalize");
        SAVE_HOOK = this;
}
}
