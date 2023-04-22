package com.application.socialhub.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name = "followers"
)
public class Followers {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(
            nullable = false
    )
    private LocalDate created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity follower;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity following;

    public Followers(LocalDate created_at, UserEntity follower, UserEntity following) {
        this.created_at = created_at;
        this.follower = follower;
        this.following = following;
    }

    public Followers() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }



    @Override
    public String toString() {
        return "Followers{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", userEntities=" + follower +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Followers followers = (Followers) o;
        return id == followers.id && Objects.equals(created_at, followers.created_at) && Objects.equals(follower, followers.follower) && Objects.equals(following, followers.following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created_at, follower, following);
    }
}
