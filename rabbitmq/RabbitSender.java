package com.dh.springredis.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback {
    @Autowired
    RabbitTemplate rabbitTemplate;

/*    public void sender(String content) {
        this.rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationId = null;
        for (int i = 0; i < 30; ++i) {
            correlationId = new CorrelationData(UUID.randomUUID().toString() + "------------" + i);
            this.rabbitTemplate.convertAndSend("fanoutExchange", "", content+i, correlationId);
            System.out.println("[x] send:" + content + "***" + i);
        }
    }*/

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println(correlationData.getId() + "接收成功");
        } else {
            System.out.println("接收失败" + s);
        }
    }

    public void send() {
//        this.rabbitTemplate.setConfirmCallback(this);
        String context = "hi, i am message all";
        System.out.println("Sender : " + context);
        for (int i = 0; i < 200; ++i)
            this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context + i);
    }

    public void send1() {
//        this.rabbitTemplate.setConfirmCallback(this);
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context);
    }

    public void send2() {
//        this.rabbitTemplate.setConfirmCallback(this);
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", context);
    }
}
