package com.application.socialhub.dto;

import java.sql.Blob;

public record EventDTO(Long eventID,
                       String message,
                       String created_at,
                       String eventReceiver,
                       String eventCreator,
                       Blob profilePhoto
                       ) {
}
