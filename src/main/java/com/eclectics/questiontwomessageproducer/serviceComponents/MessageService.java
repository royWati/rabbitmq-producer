package com.eclectics.questiontwomessageproducer.serviceComponents;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${eclectics.rabbitmq.exchange}")
    private String exchange;
    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;

    public void sendMessage(String msg){
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }
}
