package com.application.socialhub.model;

import java.sql.Blob;
import java.time.LocalDate;

public class PostsReturns {
    private String content;
    private LocalDate created_at;
    private UserEntity user_entity_id;

    public PostsReturns(String content, LocalDate created_at, UserEntity user_entity_id) {
        this.content = content;
        this.created_at = created_at;
        this.user_entity_id = user_entity_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public UserEntity getUser_entity_id() {
        return user_entity_id;
    }

    public void setUser_entity_id(UserEntity user_entity_id) {
        this.user_entity_id = user_entity_id;
    }
}
