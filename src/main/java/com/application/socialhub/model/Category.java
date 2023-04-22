package com.application.socialhub.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;


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
    private Set<PostCategory> postCategories;

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

    @Override
    public String toString() {
        return "Category{" +
                "id_category=" + id_category +
                ", name='" + name + '\'' +
                ", postCategories=" + postCategories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id_category == category.id_category && Objects.equals(name, category.name) && Objects.equals(postCategories, category.postCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_category, name, postCategories);
    }
}
