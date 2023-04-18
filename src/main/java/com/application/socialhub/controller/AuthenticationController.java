package com.application.socialhub.controller;

import com.application.socialhub.dto.AuthenticationRequest;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.service.AuthenticationService;
import com.application.socialhub.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService authenticationService,
                                    RegistrationService registrationService) {
        this.authenticationService = authenticationService;
        this.registrationService = registrationService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping(path = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirmToken")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
