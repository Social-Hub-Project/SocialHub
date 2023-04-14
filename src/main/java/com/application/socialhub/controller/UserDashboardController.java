package com.application.socialhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class UserDashboardController {

    @GetMapping("/getMyDetails")
    public ResponseEntity<String> getMyDetails(){
        String str= "getMyDetails";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/blockComments")
    public ResponseEntity<String> blockComments (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/unlockComments ")
    public ResponseEntity<String> unlockComments (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/updatePhoto")
    public ResponseEntity<String> updatePhoto(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/updateBackgroundPhoto")
    public ResponseEntity<String> updateBackgroundPhoto(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }


}
