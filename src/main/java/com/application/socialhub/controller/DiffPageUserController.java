package com.application.socialhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class DiffPageUserController {

    @GetMapping("/getUserDetails")
    public ResponseEntity<String> getUserDetails(){
        String str= "getUserDetails";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @GetMapping("/getPostsForUser")
    public ResponseEntity<String> getPostsForUser(){
        String str= "getPostsForUser";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }
}
