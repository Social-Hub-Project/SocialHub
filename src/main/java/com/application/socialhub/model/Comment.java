package com.application.socialhub.model;


import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(
        name="comment"
)
public class Comment {
    @Id
    @SequenceGenerator(
            name="my_comment_id_seq",
            sequenceName = "my_comment_id_seq",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_comment_id_seq"
    )
    private long id;
    @Column(
           columnDefinition = "TEXT",
            nullable=true
    )
    private String content;
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
            name = "id_post"
    )
    private Post post;

    public Comment(String content, LocalDate created_at, UserEntity userEntity, Post post) {
        this.content = content;
        this.created_at = created_at;
        this.userEntity = userEntity;
        this.post = post;
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
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", user=" + userEntity +
                ", post=" + post +
                '}';
    }
}