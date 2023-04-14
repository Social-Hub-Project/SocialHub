package com.application.socialhub.controller;

import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
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

    @GetMapping("/register")
    public String registerView() {
        return  "register here";
    }

}
