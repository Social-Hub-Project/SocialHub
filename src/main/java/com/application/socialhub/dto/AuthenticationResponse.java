package com.application.socialhub.dto;

public record AuthenticationResponse(String token,
                                     String name,
                                     String surname,
                                     String profilePhoto,
                                     String message) {
}
