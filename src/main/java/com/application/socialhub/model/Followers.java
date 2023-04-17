package com.application.socialhub.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,
            name = "follower_id")
    private UserInfo follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="following_id", nullable = false)
    private UserInfo following;

    public Followers(LocalDate created_at, UserInfo follower, UserInfo following) {
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
}
