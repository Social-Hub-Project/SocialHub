package com.application.socialhub.controller;

import com.application.socialhub.dto.ChangePhotoRequest;
import com.application.socialhub.dto.DefaultGetRequest;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserDetailsDTO;
import com.application.socialhub.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDTO getUserInfo(@ModelAttribute DefaultGetRequest request) {
        return  userService.getUserInfo(request);
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
