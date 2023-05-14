package com.application.socialhub.controller;

import com.application.socialhub.dto.DefaultGetRequest;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserDetailsDTO;
import com.application.socialhub.model.UserInfo;
import com.application.socialhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDetailsDTO getUserInfo(@ModelAttribute DefaultGetRequest request) {
        return  userService.getUserInfo(request);
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
