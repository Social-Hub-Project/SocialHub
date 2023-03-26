package com.application.socialhub.controller;

import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.service.RegistrationService;
import com.application.socialhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    private final RegistrationService registrationService;

    @Autowired
    public UserController(UserService userService,
                          RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
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
    public String loginView() {

        return  "login here";
    }

    @GetMapping("/register")
    public String registerView() {

        return  "register here";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        registrationService.register(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION)
                .build();
    }
}
