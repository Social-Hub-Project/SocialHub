package com.application.socialhub.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(
        name="rating"
)
public class Rating {
     @Id

     @GeneratedValue(
             strategy = GenerationType.IDENTITY)
     private long id;

     @Column(
             nullable = false,
             name="assessment"
     )
     private boolean value;
     @Column(
             nullable = false
     )
     private LocalDate modified_at;
     @Column(
             nullable = false
     )
     private LocalDate created_at;

     @ManyToOne(fetch = FetchType.LAZY)
     private UserEntity userEntity;
     @ManyToOne(fetch = FetchType.LAZY)
     private Post posts;

        public Rating(boolean value, LocalDate modified_at, LocalDate created_at, UserEntity userEntity, Post posts) {
            this.value = value;
            this.modified_at = modified_at;
            this.created_at = created_at;
            this.userEntity = userEntity;
            this.posts = posts;
        }

     public Rating() {
     }

     public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }

     public boolean isValue() {
          return value;
     }

     public void setValue(boolean value) {
          this.value = value;
     }

     public LocalDate getModified_at() {
          return modified_at;
     }

     public void setModified_at(LocalDate modified_at) {
          this.modified_at = modified_at;
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
          return "Rating{" +
                  "id=" + id +
                  ", value=" + value +
                  ", modified_at=" + modified_at +
                  ", created_at=" + created_at +
                  ", user=" + userEntity +
                  ", post=" + posts +
                  '}';
     }
}
