package com.application.socialhub.controller;

import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserDTO> getAllUsers() {
        return  userService.getAllUsers();
    }

    @GetMapping("/example")
    public String example() {
        return  "example";
    }
    @GetMapping("/login")
    public String login() {

        return  "login here";
    }

    @GetMapping("/register")
    public String register() {

        return  "register here";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody UserRegistrationRequest request) {
        userService.addUser(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION)
                .build();
    }
}
