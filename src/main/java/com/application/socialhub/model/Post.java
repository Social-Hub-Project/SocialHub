package com.application.socialhub.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name="post"
)
public class Post {
    @Id
    @SequenceGenerator(
            name="my_post_id_seq",
            sequenceName = "my_post_id_seq",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_post_id_seq"
    )
    private long id;

    @Column(
            columnDefinition = "TEXT",
            nullable = true
    )
    private String description;
    @Column(
            nullable = false
    )
    private boolean blocked;
    @Column(
            nullable = false
    )
    private LocalDate create_at;
    @Column(
            nullable = true
    )
    private String photo_source;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "id_user"
    )
    private UserEntity userEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    public Post(String description, boolean blocked, LocalDate create_at, String photo_source, UserEntity userEntity, Set<Category> categories) {
        this.description = description;
        this.blocked = blocked;
        this.create_at = create_at;
        this.photo_source = photo_source;
        this.userEntity = userEntity;
        this.categories = categories;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public String getPhoto_source() {
        return photo_source;
    }

    public void setPhoto_source(String photo_source) {
        this.photo_source = photo_source;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
