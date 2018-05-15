package com.example.combinerabbit.config.rabbitmq;

import com.example.combinerabbit.Service.UserServiceImpl;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumer {
    @Autowired
    RabbitConfig rabbitConfig;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    Gson gson;

    @Autowired
    RabbitTemplate rabbitTemplate;


   /* @Bean
    SimpleMessageListenerContainer messageListenerContainera() {
        SimpleMessageListenerContainer container = rabbitConfig.container(rabbitConfig.queuea());
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String s = new String(body);
                List<Users> list = gson.fromJson(s, new TypeToken<List<Users>>() {
                }.getType());
                try {
                    for (int i = 0; i < list.size(); ++i) {
                        Users user = (Users) list.get(i);
                        userService.insert(0, user.getName(), true);
                    }
                    System.out.println(">>>>>>>>>>");
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    System.out.println("a ------ fan kui cheng gong");
                } catch (Exception e) {
                    System.out.println(message.getMessageProperties().getDeliveryTag() + "-------");
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                }
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
                try {
                    for (int i = 0; i < list.size(); ++i) {
                        Users user = (Users) list.get(i);
                        userService.insert(0, user.getName(), true);
                    }
                    System.out.println(">>>>>>>>>>");
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    System.out.println("b ------ fan kui cheng gong");
                } catch (Exception e) {
                    System.out.println(message.getMessageProperties().getDeliveryTag() + "-------");
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                    rabbitTemplate.convertAndSend("mysql_userc", gson.toJson(s));
                    System.out.println("[x] send:----" + gson.toJson(s));

                }


//                Thread.sleep(5000);
            }
        });
        return container;
    }
*/
}
