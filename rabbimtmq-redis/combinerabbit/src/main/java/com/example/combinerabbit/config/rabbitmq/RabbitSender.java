/*
package com.example.combinerabbit.config.rabbitmq;

import com.example.combinerabbit.Service.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    Gson gson;

    public void sender(int i) {
*/
/*
        List<Users> update = userService.update();
//        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("mysql_usera", gson.toJson(update));
        rabbitTemplate.setReceiveTimeout(10000);
        System.out.println("[x] send:----"+gson.toJson(update));

        rabbitTemplate.convertAndSend("mysql_userb", gson.toJson(update));
        System.out.println("[x] send:----"+gson.toJson(update));
*//*

        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("mysql_usera", gson.toJson("a" + i));
        rabbitTemplate.convertAndSend("mysql_userb", gson.toJson("b" + i));

    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println(correlationData.getId() + "接收成功");
        } else {
            System.out.println("接收失败------" + s);
        }
    }
}
*/
