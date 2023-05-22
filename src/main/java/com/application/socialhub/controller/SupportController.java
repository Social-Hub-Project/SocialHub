package com.application.socialhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class SupportController {
    @PostMapping("/sendSupportMessage")
    public ResponseEntity<String> sendSupportMessage  (@RequestBody String str){
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
