package com.application.socialhub.dto;

import com.application.socialhub.model.Post;

import java.sql.Blob;


public record PostWithImageDTO(Blob image,
                               Post post) {
}
