package com.application.socialhub.controller;

import com.application.socialhub.dto.GetMessagesRequest;
import com.application.socialhub.dto.SendMessageRequest;
import com.application.socialhub.model.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app")
public class MessageController {

    @GetMapping(path= "/getMessagesFrom", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getMessagesForUser(@RequestBody GetMessagesRequest request) {

        return new ArrayList<Message>() ;
    }

    @PostMapping(path= "/sendMessageTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> sendMessageTo(@RequestBody SendMessageRequest request) {
        return new ArrayList<Message>() ;

    }
}
