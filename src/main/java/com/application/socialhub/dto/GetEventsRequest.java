package com.application.socialhub.dto;

public record GetEventsRequest(String token,
                               int numberOfEvents) {
}
