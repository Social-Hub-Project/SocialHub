package com.application.socialhub.dto;

import org.springframework.web.multipart.MultipartFile;

public record ChangePhotoRequest(MultipartFile photo,
                                 String token) {
}
