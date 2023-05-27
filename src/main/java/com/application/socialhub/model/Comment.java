package com.application.socialhub.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(
        name="comment"
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(
            columnDefinition = "TEXT"
    )
    private String content;
    @Column(
            nullable = false
    )
    private LocalDate created_at;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post posts;

    public Comment(String content, LocalDate created_at, UserEntity userEntity, Post posts) {
        this.content = content;
        this.created_at = created_at;
        this.userEntity = userEntity;
        this.posts = posts;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Post getPost() {
        return posts;
    }

    public void setPost(Post post) {
        this.posts = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", user=" + userEntity +
                ", post=" + posts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(content, comment.content) && Objects.equals(created_at, comment.created_at) && Objects.equals(userEntity, comment.userEntity) && Objects.equals(posts, comment.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, created_at, userEntity, posts);
    }
}