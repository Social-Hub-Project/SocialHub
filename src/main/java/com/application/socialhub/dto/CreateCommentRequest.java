package com.application.socialhub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCommentRequest (String token, String description, long idPost){
}
