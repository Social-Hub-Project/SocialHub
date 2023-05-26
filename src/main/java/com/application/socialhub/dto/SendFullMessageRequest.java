package com.application.socialhub.dto;

public record SendFullMessageRequest(String receiver,
                                     String content,
                                     String sentAt) {
}
