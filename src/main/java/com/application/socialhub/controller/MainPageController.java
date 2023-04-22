package com.application.socialhub.controller;


import com.application.socialhub.dto.CreateCommentRequest;
import com.application.socialhub.dto.CreatePostRequest;
import com.application.socialhub.dto.CreateRatingRequest;
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

    @PostMapping(value = "/ratingPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ratingPost (@ModelAttribute CreateRatingRequest request){
        return  mainPageService.ratingPost(request);
    }


    @PostMapping(path="/commentPost")
    public ResponseEntity<?> commentPost(@RequestBody CreateCommentRequest request){
        return  mainPageService.commentPost(request);
    }

    @PostMapping("/followUser")
    public ResponseEntity<String> followUser(@RequestBody String str){
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @PostMapping(path="/createPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPost (@ModelAttribute CreatePostRequest request){
        return mainPageService.createPost(request);
    }

}
