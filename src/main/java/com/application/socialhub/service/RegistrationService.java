package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.exception.DuplicateResourceException;
import com.application.socialhub.model.ConfirmationToken;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationService {

    private final UserService userService;
    private final EmailValidatorService emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;


    public RegistrationService(@Qualifier("jpa") UserDAO userDAO,
                               UserService userService,
                               EmailValidatorService emailValidator,
                               ConfirmationTokenService confirmationTokenService,
                               PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationRequest request) {

        if (!emailValidator.test(request.email())) {
            throw new IllegalStateException("email not valid");
        }

        if (userDAO.existsUserWithEmail(request.email())) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }

        User user = new User(
                Role.USER,
                request.email(),
                request.name(),
                passwordEncoder.encode(request.password()),
                LocalDate.now().toString()
        );



        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        userDAO.save(user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);





        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
    }
}
