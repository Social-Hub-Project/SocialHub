package com.application.socialhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name="post"
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            columnDefinition = "TEXT"
    )
    @Size(max = 512, message = "post description field max size is 64 characters!")
    private String description;
    @Column(
            nullable = false
    )
    private boolean blocked;
    @Column(
            nullable = false
    )
    @NotNull(message = "create_at field can't be null!")
    private LocalDate create_at;
    @Column
    private String photo_source;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "posts",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<PostCategory> postCategories;


    public Post(String description, boolean blocked, LocalDate create_at, String photo_source, UserEntity userEntity) {
        this.description = description;
        this.blocked = blocked;
        this.create_at = create_at;
        this.photo_source = photo_source;
        this.userEntity = userEntity;
    }

    public Post() {
    }

    public Post(long id, String description, boolean blocked, LocalDate create_at, String photo_source, UserEntity userEntity) {
        this.id = id;
        this.description = description;
        this.blocked = blocked;
        this.create_at = create_at;
        this.photo_source = photo_source;
        this.userEntity = userEntity;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && blocked == post.blocked && Objects.equals(description, post.description) && Objects.equals(create_at, post.create_at) && Objects.equals(photo_source, post.photo_source) && Objects.equals(userEntity, post.userEntity) && Objects.equals(comments, post.comments) && Objects.equals(postCategories, post.postCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, blocked, create_at, photo_source, userEntity, comments, postCategories);
    }
}