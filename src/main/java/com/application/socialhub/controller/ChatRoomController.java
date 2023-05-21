package com.application.socialhub.controller;

import com.application.socialhub.model.ChatRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ChatRoomController {

    @PostMapping
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoom chatRoom) {

    }
}
