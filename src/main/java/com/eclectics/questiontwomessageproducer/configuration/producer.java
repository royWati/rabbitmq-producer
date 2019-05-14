package com.eclectics.questiontwomessageproducer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class producer {

    @Value("${eclectics.rabbitmq.queue}")
    private String queueName;
    @Value("${eclectics.rabbitmq.exchange}")
    private String exchangeName;
    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    Queue queue(){
        return new Queue(queueName,true);
    }
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }
    @Bean
    Binding binding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }
}
