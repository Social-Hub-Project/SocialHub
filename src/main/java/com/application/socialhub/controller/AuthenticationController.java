package com.application.socialhub.controller;

import com.application.socialhub.dto.AuthenticationRequest;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.service.AuthenticationService;
import com.application.socialhub.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path="/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    RegistrationService registrationService) {
        this.authenticationService = authenticationService;
        this.registrationService = registrationService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.login(request);
    }

    @PostMapping(path = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new LinkedList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return registrationService.register(request);
    }

    @GetMapping(path = "confirmToken")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}