package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.EventDTO;
import com.application.socialhub.model.Event;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Function;

import static com.application.socialhub.dtoMappers.BasicUserInfoDTOMapper.convertImagePathToImage;

public class EventDTOMapper implements Function<Event, EventDTO> {
    @Override
    public EventDTO apply(Event event) {
        try {
            return new EventDTO(event.getId(),
                                event.getMessage(),
                                event.getCreated_at().toString(),
                     event.getEventReceiver().getUserInfo().getName() + " " +
                                event.getEventReceiver().getUserInfo().getSurname(),
                    event.getEventCreator().getUserInfo().getName() + " " +
                                event.getEventCreator().getUserInfo().getSurname(),
                    convertImagePathToImage(event.getEventCreator().getUserInfo().getProfilePhotoSource()));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
