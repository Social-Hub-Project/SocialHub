package com.application.socialhub.dto;


import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;


public record CreatePostRequest(String token,
                                @Size(max = 512, message = "post description field max size is 64 characters!")
                                String description,
                                MultipartFile image) {
}
