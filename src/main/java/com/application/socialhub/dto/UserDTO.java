package com.application.socialhub.dto;

import com.application.socialhub.model.Role;

public record UserDTO(
        String email,
        Role role,
        Boolean active

) {

}
