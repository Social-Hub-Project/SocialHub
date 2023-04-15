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
     @SequenceGenerator(
             name="my_rat_id_seq",
             sequenceName = "my_rat_id_seq",
             allocationSize = 1,
             initialValue = 0
     )
     @GeneratedValue(
             strategy = GenerationType.SEQUENCE,
             generator = "my_rat_id_seq"
     )
     private long id;

     @Column(
             nullable = false
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

     public Rating(boolean value, LocalDate modified_at, LocalDate created_at, UserEntity userEntity, Post post) {
          this.value = value;
          this.modified_at = modified_at;
          this.created_at = created_at;
          this.userEntity = userEntity;
          this.post = post;
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
          return post;
     }

     public void setPost(Post post) {
          this.post = post;
     }

     @Override
     public String toString() {
          return "Rating{" +
                  "id=" + id +
                  ", value=" + value +
                  ", modified_at=" + modified_at +
                  ", created_at=" + created_at +
                  ", user=" + userEntity +
                  ", post=" + post +
                  '}';
     }
}
