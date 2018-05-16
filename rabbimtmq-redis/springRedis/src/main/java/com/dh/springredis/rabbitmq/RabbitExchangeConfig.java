package com.dh.springredis.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitExchangeConfig {
    //直连交换机
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("gggg");
    }

    //队列
    @Bean
    public Queue queue() {
        return QueueBuilder.durable("ssss").build();
    }

    //绑定
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(this.queue()).to(this.defaultExchange()).with("error");
    }
}
