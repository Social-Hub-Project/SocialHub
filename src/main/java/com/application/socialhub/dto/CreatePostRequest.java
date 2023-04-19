package com.application.socialhub.dto;


import org.springframework.web.multipart.MultipartFile;

public record CreatePostRequest(String token,
                                String description,
                                MultipartFile image) {
}
