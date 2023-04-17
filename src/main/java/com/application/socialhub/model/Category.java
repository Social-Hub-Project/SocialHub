package com.application.socialhub.model;

import jakarta.persistence.*;


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
