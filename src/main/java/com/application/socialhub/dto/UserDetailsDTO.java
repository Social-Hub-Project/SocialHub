package com.application.socialhub.dto;

import com.application.socialhub.model.Sex;
import java.sql.Blob;

public record UserDetailsDTO(
        Long id,
        String name,
        String surname,
        java.time.LocalDate dateOfBirth,
        String residence,
        Sex sex,
        java.time.LocalDate createdAt,
        Blob profilePhoto,
        Blob backgroundPhoto,
        String profilePhotoSource,
        String backgroundPhotoSource,
        boolean followed
){
}
