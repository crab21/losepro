package com.example.combinerabbit.Controller;

import com.example.combinerabbit.config.scheduled.ScheduledLearn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    @Autowired
    ScheduledLearn scheduledLearn;

    @RequestMapping("/start")
    public String startCron() {
        scheduledLearn.startCron();
        return "start";
    }

    @RequestMapping("/stop")
    public String stopCron() {
        scheduledLearn.startCron();
        return "stop";
    }

    @RequestMapping("/restart")
    public String restartCron() {
        scheduledLearn.startCron();
        return "restart";
    }
}
