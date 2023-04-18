package com.application.socialhub.dto;


public record CreatePostRequest(String token,
                                String description,
                                String photo) {
}
