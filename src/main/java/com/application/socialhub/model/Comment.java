package com.application.socialhub.model;


import jakarta.persistence.*;

import java.sql.Date;


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
            name = "id_post"
    )
    private Post post;

    public Comment(String content, Date created_at, User user, Post post) {
        this.content = content;
        this.created_at = created_at;
        this.user = user;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public com.application.socialhub.model.User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}