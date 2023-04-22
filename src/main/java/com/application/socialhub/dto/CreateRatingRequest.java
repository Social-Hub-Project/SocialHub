package com.application.socialhub.dto;

public record CreateRatingRequest(String token, boolean like, long idPost) {
}
