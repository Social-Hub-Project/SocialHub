package com.application.socialhub.dto;

import com.application.socialhub.model.Sex;

public record UserDetailsDTO(
        String surname,
        java.sql.Date dateOfBirth,
        String residence,
        Sex sex
){
}
