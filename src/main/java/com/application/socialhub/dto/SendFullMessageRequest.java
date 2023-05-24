package com.application.socialhub.dto;

public record SendFullMessageRequest(String sender,
                                     String receiver,
                                     String content,
                                     String sentAt) {
}
