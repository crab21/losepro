package com.example.combinerabbit.plugin.rabbitmq;

import com.example.combinerabbit.Service.UserServiceImpl;
import com.example.combinerabbit.model.Users;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback{
    @Autowired
        RabbitTemplate rabbitTemplate;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    Gson gson;

    public void sender(){
        List<Users> update = userService.update();
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("mysql_usera", gson.toJson(update));
        rabbitTemplate.setReceiveTimeout(10000);
        System.out.println("[x] send:----"+gson.toJson(update));

        rabbitTemplate.convertAndSend("mysql_userb", gson.toJson(update));
        System.out.println("[x] send:----"+gson.toJson(update));

    }



    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
            if(b){
                System.out.println("接收成功");
            }else{
                System.out.println("接收失败------"+s);
            }
    }
}
