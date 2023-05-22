package com.application.socialhub.controller;

import com.application.socialhub.dto.SendMessageRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ChatRoomController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public SendMessageRequest send(final SendMessageRequest message) throws Exception {

        return message;
    }
}
