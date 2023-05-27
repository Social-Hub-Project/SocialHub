package com.application.socialhub.controller;


import com.application.socialhub.dto.*;
import com.application.socialhub.service.MainPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class MainPageController {

    private MainPageService mainPageService;

    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }


    @PostMapping(path = "/searchUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchUser(@RequestBody SearchUserRequest request,Authentication aut){
        return mainPageService.searchUser(request.word(),aut);
    }
    @GetMapping(value = "/getFriendsList",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFriendsList(Authentication authentication){
        return mainPageService.getFriendsList(authentication);
    }

    @GetMapping(path ="/getAllPosts")
    public ResponseEntity<?> getAllPosts(Authentication authentication){
        return mainPageService.getAllPosts(authentication);
    }

    @PostMapping(value = "/getLastEvents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLastEvents(@RequestBody GetEventsRequest request){
        return mainPageService.getLastEvents(request);
    }

    @PostMapping(value = "/ratingPost")
    public ResponseEntity<?> ratingPost (@RequestBody CreateRatingRequest request){
        return  mainPageService.ratingPost(request);
    }

    @PostMapping(path="/commentPost")
    public ResponseEntity<?> commentPost(@RequestBody CreateCommentRequest request){
        return  mainPageService.commentPost(request);
    }

    @PostMapping(value="/followUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> followUser(@RequestBody FollowUserRequest request){
        return mainPageService.followUser(request);
    }
    @PostMapping(value = "/unfollowUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unfollowUser(@RequestBody FollowUserRequest request){
        return mainPageService.unfollowUser(request);
    }

    @PostMapping(path="/createPost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPost (@ModelAttribute CreatePostRequest request){
        return mainPageService.createPost(request);
    }

}
