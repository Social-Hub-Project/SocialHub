package com.application.socialhub.dto;

public record AuthenticationResponse(String token,
                                     UserDTO userDTO) {
}
