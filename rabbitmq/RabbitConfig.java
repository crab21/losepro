package com.dh.springredis.rabbitmq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "spring-boot-exchange";
    public static final String ROUTINGKEY = "spring-boot-routingKey";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("127.0.0.1");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        cachingConnectionFactory.setVirtualHost("/");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setPublisherConfirms(true);
        return cachingConnectionFactory;
    }


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    @Autowired
    RabbitQueueAndExchange queueAndExchange;

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = common(queueAndExchange.queuea());
        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) {
                byte[] body = message.getBody();
                System.out.println("A-------------receive msg : " + new String(body));
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A"+"--------------------fan kui wan cheng +++++++++" + new String(body));
                }
            }
        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageContainerb() {
        SimpleMessageListenerContainer container = common(queueAndExchange.queueb());
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) {
                byte[] body = message.getBody();
                System.out.println("B-------------receive msg : " + new String(body));
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B"+"--------------------fan kui wan cheng +++++++++" + new String(body));
                }
            }
        });
        return container;
    }


    private SimpleMessageListenerContainer common(Queue queue){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);

        container.setPrefetchCount(200);

        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        return  container;
    }
    /*@Bean
    public SimpleMessageListenerContainer messageContainerc() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queueAndExchange.queuec());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setPrefetchCount(1);

        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) {
                byte[] body = message.getBody();
                System.out.println("C-------------receive msg : " + new String(body));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("C"+"--------------------fan kui wan cheng +++++++++" + new String(body));
                }
            }
        });
        return container;
    }*/
}
