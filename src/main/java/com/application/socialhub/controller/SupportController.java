package com.application.socialhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportController {
    @PostMapping("/app/sendSupportMessage")
    public ResponseEntity<String> sendSupportMessage  (@RequestBody String str){
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
