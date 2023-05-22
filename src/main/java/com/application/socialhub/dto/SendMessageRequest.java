package com.application.socialhub.dto;

public record SendMessageRequest(String token,
                                 String receiver,
                                 String content) {
}
