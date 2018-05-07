package com.dh.springredis.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueAndExchange {

    /**
     *  发布订阅
     */
/*    @Bean
    public Queue queuea() {
        return new Queue("helloa", true);
    }

    @Bean
    public Queue queueb() {
        return new Queue("hellob", true);
    }

    @Bean
    public Queue queuec() {
        return new Queue("helloc", true);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue queuea,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queuea).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue queueb,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueb).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue queuec,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queuec).to(fanoutExchange);
    }*/

    /**
     * topic模式
     */
    final String messageA = "topic.a";
    final String messageB = "topic.b";

    @Bean
    Queue queuea() {
        return new Queue(messageA);
    }

    @Bean
    Queue queueb() {
        return new Queue(messageB);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding binding(Queue queuea, TopicExchange exchange) {
        return BindingBuilder.bind(queuea).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueb, TopicExchange exchange) {
        return BindingBuilder.bind(queueb).to(exchange).with("topic.#");
    }


}
