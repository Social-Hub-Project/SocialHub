package com.application.socialhub.dto;

import com.application.socialhub.model.Sex;

import java.time.LocalDate;

public record UserDetailsDTO(
         String surname,
         LocalDate dateOfBirth,
         String residence,
         Sex sex
){
}
