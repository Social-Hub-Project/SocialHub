package com.application.socialhub.controller;

import com.application.socialhub.dto.*;
import com.application.socialhub.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return  userService.getAllUsers();
    }

    @PostMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDTO getUserInfo(@RequestBody DefaultGetRequest request) {
        return  userService.getUserInfo(request);
    }

    @PostMapping(path = "/getUserInfoById", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDTO getUserInfoById(@RequestBody UserInfoIdRequest request) {
        return  userService.getUserInfoById(request);
    }

    @PostMapping(path = "/changeProfilePhoto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeProfilePhoto(@ModelAttribute ChangePhotoRequest request) {
        return userService.changeProfilePhoto(request);
    }

    @PostMapping(path = "/changeBackgroundPhoto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeBackgroundPhoto(@ModelAttribute ChangePhotoRequest request) {
        return userService.changeBackgroundPhoto(request);
    }

}
