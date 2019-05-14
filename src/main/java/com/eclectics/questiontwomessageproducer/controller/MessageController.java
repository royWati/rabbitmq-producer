package com.eclectics.questiontwomessageproducer.controller;

import com.eclectics.questiontwomessageproducer.serviceComponents.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService service;
    @PostMapping("/{message}")
    public void sendMessage(@PathVariable String message){
        service.sendMessage(message);
    }
}
