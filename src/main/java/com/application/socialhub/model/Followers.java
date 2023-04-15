package com.application.socialhub.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(
        name = "followers"
)
public class Followers {
    @Id
    @SequenceGenerator(
            name="my_foll_id_seq",
            sequenceName = "my_foll_id_seq",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_foll_id_seq"
    )
    private long id;

    @Column(
            nullable = false
    )
    private LocalDate created_at;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "id_user"
    )
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "id_follower"
    )
    private UserEntity follower;

    public Followers(LocalDate created_at, UserEntity userEntity, UserEntity follower) {
        this.created_at = created_at;
        this.userEntity = userEntity;
        this.follower = follower;
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

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUser_f() {
        return follower;
    }

    public void setUser_f(UserEntity follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", user=" + userEntity +
                ", follower=" + follower +
                '}';
    }
}
