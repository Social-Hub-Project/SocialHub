package com.application.socialhub.controller;

import com.application.socialhub.model.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MessageController {

    @GetMapping(path= "/getMessagesFrom", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getMessagesForUser(@RequestBody ) {

    }

    @PostMapping(path= "/sendMessageTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getMessagesForUser(@RequestBody ) {

    }
}
