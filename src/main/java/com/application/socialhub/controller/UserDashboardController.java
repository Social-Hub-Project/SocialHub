package com.application.socialhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDashboardController {

    @GetMapping("/app/getMyDetails")
    public ResponseEntity<String> getMyDetails(){
        String str= "getMyDetails";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/resetPassword")
    public ResponseEntity<String> resetPassword (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/blockComments")
    public ResponseEntity<String> blockComments (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/unlockComments ")
    public ResponseEntity<String> unlockComments (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/updatePhoto")
    public ResponseEntity<String> updatePhoto(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/updateBackgroundPhoto")
    public ResponseEntity<String> updateBackgroundPhoto(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }


}
