package com.application.socialhub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(
        name = "user_details"
)
public class UserInfo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private long id;
    @Column(
            nullable = false
    )
    @NotBlank(message = "name field can't be blank!")
    @Size(max = 128, message = "name field max size is 128!")
    private String name;
    @Column(
            nullable = false
    )
    @NotBlank(message = "surname field can't be blank!")
    @Size(max = 128, message = "surname field max size is 128!")
    private String surname;
    @Column(
            nullable = false
    )
    @NotNull(message = "dateOfBirth field can't be null!")
    private LocalDate dateOfBirth;
    @Column(
            nullable = false
    )
    @NotBlank(message = "residence field can't be blank!")
    @Size(max = 128, message = "residence field max size is 128 characters!")
    private String residence;
    @Column(
            nullable = false
    )
    private boolean blocked;
    @Column(
            columnDefinition = "TEXT"
    )
    private String profilePhotoSource;
    @Column(
            columnDefinition = "TEXT"
    )
    private String bgPhotoSource;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "sex field can't be null!")
    private Sex sex;
    @Column(
            nullable = false
    )
    @NotNull(message = "createdAt field can't be null!")
    private LocalDate createdAt;

    @OneToOne(mappedBy = "userInfo",fetch =FetchType.EAGER)
    private UserEntity userEntities;

    public UserInfo(String name, String surname, LocalDate dateOfBirth, String residence, boolean blocked,
                    String profilePhotoSource, String bgPhotoSource, Sex sex, LocalDate createdAt) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.residence = residence;
        this.blocked = blocked;
        this.profilePhotoSource = profilePhotoSource;
        this.bgPhotoSource = bgPhotoSource;
        this.sex = sex;
        this.createdAt = createdAt;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getProfilePhotoSource() {
        return profilePhotoSource;
    }

    public void setProfilePhotoSource(String profilePhotoSource) {
        this.profilePhotoSource = profilePhotoSource;
    }

    public String getBgPhotoSource() {
        return bgPhotoSource;
    }

    public void setBgPhotoSource(String bgPhotoSource) {
        this.bgPhotoSource = bgPhotoSource;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", residence='" + residence + '\'' +
                ", blocked=" + blocked +
                ", profilePhotoSource='" + profilePhotoSource + '\'' +
                ", bgPhotoSource='" + bgPhotoSource + '\'' +
                ", sex=" + sex +
                ", createdAt=" + createdAt +
                '}';
    }

    public long getId() {
        return id;
    }
}
