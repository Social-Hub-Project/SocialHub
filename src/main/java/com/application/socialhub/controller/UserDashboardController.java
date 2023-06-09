package com.application.socialhub.controller;

import com.application.socialhub.dto.AllPostsForUserRequest;
import com.application.socialhub.dto.DeletePostRequest;
import com.application.socialhub.service.UserDashboardService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class UserDashboardController {

    private final UserDashboardService userDashboardService;

    public UserDashboardController(UserDashboardService userDashboardService) {
        this.userDashboardService = userDashboardService;
    }

    @PostMapping(value ="/blockComments",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> blockComments (@RequestBody DeletePostRequest request){
        return userDashboardService.blockComments(request);
    }

    @PostMapping(value ="/unlockComments",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unlockComments (@RequestBody DeletePostRequest request){
        return userDashboardService.unlockComments(request);
    }

    @DeleteMapping (value ="/deletePost",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePost(@RequestBody DeletePostRequest request){
        return userDashboardService.deletePost(request);
    }

    @PostMapping(value ="/getAllPostsForUser",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPostsForUser(@RequestBody AllPostsForUserRequest request){
        return userDashboardService.getAllPostsForUser(request);
    }


}
