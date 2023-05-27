package com.application.socialhub.dto;

public record EventDTO(Long eventID,
                       String message,
                       String created_at,
                       String eventReceiver,
                       String eventCreator
                       ) {
}
