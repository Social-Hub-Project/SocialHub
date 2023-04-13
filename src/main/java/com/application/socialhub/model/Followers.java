package com.application.socialhub.model;

import jakarta.persistence.*;

import java.sql.Date;

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
    private Date created_at;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "id_user"
    )
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "id_follower"
    )
    private User follower;

    public Followers(Date created_at, User user, User follower) {
        this.created_at = created_at;
        this.user = user;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser_f() {
        return follower;
    }

    public void setUser_f(User follower) {
        this.follower = follower;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", user=" + user +
                ", follower=" + follower +
                '}';
    }
}
