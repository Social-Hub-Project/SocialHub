package com.application.socialhub.model;

import jakarta.persistence.*;

import java.util.ArrayList;


@Entity
@Table(
        name="category"
)
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id_category;

    @Column(
        nullable=false
    )
    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    private ArrayList<PostCategory> postCategories;

    public Category(String name) {
        this.name = name;
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

}
