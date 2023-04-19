package com.application.socialhub.controller;


import com.application.socialhub.dto.CreatePostRequest;
import com.application.socialhub.dto.PostDTO;
import com.application.socialhub.service.MainPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class MainPageController {

    private MainPageService mainPageService;

    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

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

    @GetMapping(path ="/getAllPosts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPosts(){
        return mainPageService.getAllPosts();
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

    @PostMapping(path="/createPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPost (@RequestBody CreatePostRequest request){
        return mainPageService.createPost(request);
    }

}
