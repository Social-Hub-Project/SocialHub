package com.application.socialhub.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainFunctionController {

    @GetMapping("/app/getFriendsList")
    public ResponseEntity<String> getFriendsList(){
        String str= "getFriendsList";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @GetMapping("/app/getSearchedPeople")
    public ResponseEntity<String> getSearchedPeople(){
        String str= "getSearchedPeople";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/app/getPosts")
    public ResponseEntity<String> getPosts(){
        String str= "getPosts";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/app/getLastEvents")
    public ResponseEntity<String> getLastEvents(){
        String str= "getLastEvents";
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/likePost")
    public ResponseEntity<String> likePost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/dislikePost")
    public ResponseEntity<String> dislikePost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }


    @PostMapping("/app/commentPost")
    public ResponseEntity<String> commentPost(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/followUser")
    public ResponseEntity<String> followUser(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping("/app/createPost")
    public ResponseEntity<String> createPost (@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

}
