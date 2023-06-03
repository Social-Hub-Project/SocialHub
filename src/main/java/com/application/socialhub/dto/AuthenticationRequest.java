package com.application.socialhub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
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
                                    String password) {
}
