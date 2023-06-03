package com.application.socialhub.dao;

import com.application.socialhub.model.Event;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("event")
public class EventJPADataAccessService implements EventDAO{

    private final EventRepository repository;

    public EventJPADataAccessService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> getLastEvents(UserEntity eventReceiver, int numberOfEvents) {
        return repository.getLastEvents(eventReceiver.getId(), numberOfEvents);
    }

    @Override
    public void saveEvent(Event event) {
        repository.save(event);
    }
}
