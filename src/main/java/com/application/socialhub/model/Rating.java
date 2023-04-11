package com.application.socialhub.model;

import jakarta.persistence.*;

import java.sql.Date;


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
     private Date modified_at;
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

     public Rating(boolean value, Date modified_at, Date created_at, User user, Post post) {
          this.value = value;
          this.modified_at = modified_at;
          this.created_at = created_at;
          this.user = user;
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

     public Date getModified_at() {
          return modified_at;
     }

     public void setModified_at(Date modified_at) {
          this.modified_at = modified_at;
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
                  ", user=" + user +
                  ", post=" + post +
                  '}';
     }
}
