package com.example.combinerabbit.Controller;

import com.example.combinerabbit.Service.UserServiceImpl;
import com.example.combinerabbit.config.rabbitmq.RabbitSender;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RabbitSender rabbitSender;

    @RequestMapping("/wel")
    @ResponseBody
    public String wel() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 200; ++i)
                    userService.insert(0, "name1");
                System.out.println("执行完成");
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                userService.update();
                System.out.println("执行完成");
            }
        });
        thread1.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成");
        return "ok";
    }

    @RequiresPermissions("update")
    @RequestMapping("/rabb")
    @ResponseBody
    public String test2() {
        for (int i = 0; i < 10000; ++i) {

            rabbitSender.sender(i);
        }
        return "ok";
    }
}
