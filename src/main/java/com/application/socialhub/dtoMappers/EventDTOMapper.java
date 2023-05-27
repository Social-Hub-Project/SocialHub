package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.EventDTO;
import com.application.socialhub.model.Event;

import java.util.function.Function;

public class EventDTOMapper implements Function<Event, EventDTO> {
    @Override
    public EventDTO apply(Event event) {
        return new EventDTO(event.getId(),
                            event.getMessage(),
                            event.getCreated_at().toString(),
                            event.getEventReceiver().getUserInfo().getName() + " " +
                                    event.getEventReceiver().getUserInfo().getSurname(),
                            event.getEventCreator().getUserInfo().getName() + " " +
                                    event.getEventCreator().getUserInfo().getSurname());
    }
}
