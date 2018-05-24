package com.example.combinerabbit.config.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduledLearn {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private static ScheduledFuture scheduledFuture;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public void startCron() {
        scheduledFuture = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/5 * * ? * *"));
        System.out.println("定时线程启动");
    }

    public void stopCron() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            System.out.println("定时线程已经停止----");
        }
    }

    public void restratCron() {
        stopCron();
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/10 * * ? * *"));
        System.out.println("定时线程重新启动");
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 200; ++i) {
                System.out.println(i + "----------");
            }
        }
    }
}
