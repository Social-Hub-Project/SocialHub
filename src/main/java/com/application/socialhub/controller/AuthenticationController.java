package com.application.socialhub.controller;

import com.application.socialhub.dto.AuthenticationRequest;
import com.application.socialhub.dto.AuthenticationResponse;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.service.AuthenticationService;
import com.application.socialhub.service.RegistrationService;
import com.application.socialhub.util.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    JWTUtil jwtUtil,
                                    RegistrationService registrationService) {
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
        this.registrationService = registrationService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.login(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.token())
                .body(response);
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        registrationService.register(request);
        String jwtToken = jwtUtil.issueToken(request.email(), "ROLE_USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }
}
