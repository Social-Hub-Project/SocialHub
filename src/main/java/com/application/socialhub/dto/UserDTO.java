package com.application.socialhub.dto;

public record UserDTO(
        String email,
        String name,
        String surname,
        Boolean active
) {

}
