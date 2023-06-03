package com.application.socialhub.controller;

import com.application.socialhub.dto.*;
import com.application.socialhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping("/app")
public class UserController {
    private final UserService userService;
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
    public UserDetailsDTO getUserInfoById(@RequestBody UserInfoIdRequest request,Authentication authentication) {
        return  userService.getUserInfoById(request,authentication);
    }

    @PostMapping(path = "/changeProfilePhoto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeProfilePhoto(@ModelAttribute ChangePhotoRequest request) {
        return userService.changeProfilePhoto(request);
    }

    @PostMapping(path = "/changeBackgroundPhoto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeBackgroundPhoto(@ModelAttribute ChangePhotoRequest request) {
        return userService.changeBackgroundPhoto(request);
    }

    @PostMapping(path = "/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request);
    }

}
