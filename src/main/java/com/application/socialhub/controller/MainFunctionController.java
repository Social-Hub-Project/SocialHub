package com.application.socialhub.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class MainFunctionController {

    @GetMapping("/getFriendsList")
    public ResponseEntity<String> getFriendsList(){
        String str= "getFriendsList";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @GetMapping("/getSearchedPeople")
    public ResponseEntity<String> getSearchedPeople(){
        String str= "getSearchedPeople";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/getPosts")
    public ResponseEntity<String> getPosts(){
        String str= "getPosts";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/getLastEvents")
    public ResponseEntity<String> getLastEvents(){
        String str= "getLastEvents";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/likePost")
    public ResponseEntity<String> likePost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/dislikePost")
    public ResponseEntity<String> dislikePost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }


    @PostMapping("/commentPost")
    public ResponseEntity<String> commentPost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/followUser")
    public ResponseEntity<String> followUser(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/createPost")
    public ResponseEntity<String> createPost (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

}
