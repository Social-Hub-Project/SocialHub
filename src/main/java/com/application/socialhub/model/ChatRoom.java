package com.application.socialhub.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<UserEntity> participants;

    public ChatRoom(Long id, List<UserEntity> participants) {
        this.id = id;
        this.participants = participants;
    }

    public ChatRoom(List<UserEntity> participants) {
        this.participants = participants;
    }

    public ChatRoom() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
    }
}
