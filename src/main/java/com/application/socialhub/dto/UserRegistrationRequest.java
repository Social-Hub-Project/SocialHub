package com.application.socialhub.dto;

import com.application.socialhub.model.Sex;
import jakarta.validation.constraints.*;


public record UserRegistrationRequest(
        @NotBlank(message = "name field can't be blank!")
        @Size(max = 128, message = "name field max size is 128!")
        String name,
        @NotBlank(message = "surname field can't be blank!")
        @Size(max = 128, message = "surname field max size is 128!")
        String surname,
        @Email(message = "wrong email!")
        @Pattern(regexp = ".+@.+\\..+", message = "wrong email pattern!")
        @NotBlank(message = "email field can't be blank!")
        @Size(min = 5, max = 64, message = "email field size is between 5 and 64 characters!")
        String email,
        @NotBlank(message = "password field can't be blank!")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–\\[\\]{}:;',?/*~$^+=<>]).{8,64}$",
                message = "Wrong password pattern!\n" +
                        "Provide at least 1 letter, 1 capital letter, 1 digit, 1 special char,\n" +
                        "password length is between 8 and 64 chars!\n ")
        String password,

        @NotBlank(message = "password field can't be blank!")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–\\[\\]{}:;',?/*~$^+=<>]).{8,64}$",
                message = "Wrong password pattern!\n" +
                        "Provide at least 1 letter, 1 capital letter, 1 digit, 1 special char,\n" +
                        "password length is between 8 and 64 chars!\n ")
        String passwordConfirmation,
        @NotNull(message = "sex field can't be null!")
        Sex sex,
        @NotBlank(message = "residence field can't be blank!")
        @Size(max = 128, message = "residence field max size is 128 characters!")
        String residence,
        @NotBlank(message = "dateOfBirth field can't be blank!")
        String dateOfBirth
        ) {
}
