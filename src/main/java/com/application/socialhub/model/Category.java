package com.application.socialhub.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name="category"
)
public class Category {
    @Id
    @SequenceGenerator(
            name="my_cat_id_seq",
            sequenceName = "my_cat_id_seq",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_cat_id_seq"
    )
    private long id_category;

    @Column(
        nullable=false
    )
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts = new HashSet<>();

    public Category(String name, Set<Post> posts) {
        this.name = name;
        this.posts = posts;
    }

    public Category() {
    }

    public long getId_category() {
        return id_category;
    }

    public void setId_category(long id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
