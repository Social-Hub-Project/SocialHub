package com.application.socialhub.dto;

import com.application.socialhub.model.Sex;

public record UserDetailsDTO(
        String surname,
        java.time.LocalDate dateOfBirth,
        String residence,
        Sex sex
){
}
