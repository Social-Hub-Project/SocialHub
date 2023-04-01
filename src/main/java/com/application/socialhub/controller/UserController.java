package com.application.socialhub.controller;

import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.service.RegistrationService;
import com.application.socialhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/register")
    public String registerView() {
        return  "register here";
    }

    @GetMapping(path = "/confirmToken")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
