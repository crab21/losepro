/*
package com.example.combinerabbit.config.rabbitmq;

import com.example.combinerabbit.Service.UserServiceImpl;
import com.example.combinerabbit.model.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitConsumer {
    @Autowired
    RabbitConfig rabbitConfig;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    Gson gson;


    @Bean
    SimpleMessageListenerContainer messageListenerContainera() {
        SimpleMessageListenerContainer container = rabbitConfig.container(rabbitConfig.queuea());
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String s = new String(body);
                List<Users> list = gson.fromJson(s, new TypeToken<List<Users>>() {
                }.getType());
                for (int i = 0; i < list.size(); ++i) {
                    Users user = (Users) list.get(i);
                    userService.insert(0, user.getName(), true);
                }
                System.out.println(">>>>>>>>>>");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

//                Thread.sleep(5000);
                System.out.println("a ------ fan kui cheng gong");
            }
        });
        return container;
    }

    @Bean
    SimpleMessageListenerContainer messageListenerContainerb() {
        SimpleMessageListenerContainer container = rabbitConfig.container(rabbitConfig.queueb());
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String s = new String(body);
                List<Users> list = gson.fromJson(s, new TypeToken<List<Users>>() {
                }.getType());
                for (int i = 0; i < list.size(); ++i) {
                    Users user = (Users) list.get(i);
                    userService.insert(0, user.getName(), true);
                }
                System.out.println(">>>>>>>>>>");
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                System.out.println("b ------ fan kui cheng gong");
//                Thread.sleep(5000);
            }
        });
        return container;
    }

}
*/
