package com.dhlk.airsensor.conf.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduleAirSensor {

    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    private ScheduledFuture scheduledFuture;

    private static String cronTrigger = "";

    @Bean
    public ThreadPoolTaskScheduler setTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 定时启动
     */
    public void startCron() {
        scheduledFuture = taskScheduler.schedule(new CronThreadSchedule(), new CronTrigger(cronTrigger));
    }

    /**
     * 定时停止
     */
    public void stopCron() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    /**
     * 定时器重新启动
     */
    public void restartCron() {
        stopCron();
        scheduledFuture = taskScheduler.schedule(new CronThreadSchedule(), new CronTrigger(cronTrigger));
    }
}
