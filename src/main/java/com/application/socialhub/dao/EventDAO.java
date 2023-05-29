package com.application.socialhub.dao;

import com.application.socialhub.model.Event;
import com.application.socialhub.model.UserEntity;

import java.util.List;

public interface EventDAO {
    List<Event> getLastEvents(UserEntity eventReceiver, int numberOfEvents);

    void saveEvent(Event event);
}
