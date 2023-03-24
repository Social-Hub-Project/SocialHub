package com.application.socialhub.dto;

import com.application.socialhub.model.Sex;

import java.time.LocalDate;

public record UserRegistrationRequest(
        String name,
        String surname,
        String email,
        String password,
        Sex sex,
        String residence,
        LocalDate dateOfBirth

        ) {
}
