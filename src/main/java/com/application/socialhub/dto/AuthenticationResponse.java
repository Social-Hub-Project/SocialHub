package com.application.socialhub.dto;


public record AuthenticationResponse(String token,
                                     UserDetailsDTO userDetails,
                                     String message) {
}
