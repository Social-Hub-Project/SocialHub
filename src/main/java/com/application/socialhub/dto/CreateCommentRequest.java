package com.application.socialhub.dto;

public record CreateCommentRequest (String token, String description, long idPost){
}
