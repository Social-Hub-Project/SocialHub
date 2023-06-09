package com.application.socialhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/admin")
public class AdminDashboardController {
    @PostMapping("/banUser")
    public ResponseEntity<String> banUser(@RequestBody String str){
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

}
