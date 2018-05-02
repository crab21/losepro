package com.dh.springredis.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitMq;

    public void send(){
        String content = "hello mq!"+new Date().toString();
        System.out.println(content);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.rabbitMq.convertAndSend("cood",content);
    }
}
