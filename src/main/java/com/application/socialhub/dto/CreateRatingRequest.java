package com.application.socialhub.dto;

public record CreateRatingRequest(String token, int like, long idPost) {
}
