package com.application.socialhub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "events"
)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            nullable = false
    )
    private LocalDateTime created_at;

    @Column(
            nullable = false
    )
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity eventReceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity eventCreator;

    public Event() {
    }

    public Event(LocalDateTime created_at, String message, UserEntity eventReceiver, UserEntity eventCreator) {
        this.created_at = created_at;
        this.message = message;
        this.eventReceiver = eventReceiver;
        this.eventCreator = eventCreator;
    }

    public Event(long id, LocalDateTime created_at, String message, UserEntity eventReceiver, UserEntity eventCreator) {
        this.id = id;
        this.created_at = created_at;
        this.message = message;
        this.eventReceiver = eventReceiver;
        this.eventCreator = eventCreator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getEventReceiver() {
        return eventReceiver;
    }

    public void setEventReceiver(UserEntity eventReceiver) {
        this.eventReceiver = eventReceiver;
    }

    public UserEntity getEventCreator() {
        return eventCreator;
    }

    public void setEventCreator(UserEntity eventCreator) {
        this.eventCreator = eventCreator;
    }
}
