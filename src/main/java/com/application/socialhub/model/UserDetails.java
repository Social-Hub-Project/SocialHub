package com.application.socialhub.model;

import java.time.LocalDate;

public class UserDetails {

    private long id;

    private long id_user;


    private String surname;

    private LocalDate dateOfBirth;

    private String residence;

    private String profilePhotoSource;

    private String bgPhotoSource;

    private Sex sex;

    private LocalDate createdAt;

    public UserDetails() {
    }

    public UserDetails(long id_user,
                       String surname,
                       LocalDate dateOfBirth,
                       String residence,
                       String profilePhotoSource,
                       String bgPhotoSource,
                       Sex sex,
                       LocalDate createdAt) {
        this.id_user = id_user;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.residence = residence;
        this.profilePhotoSource = profilePhotoSource;
        this.bgPhotoSource = bgPhotoSource;
        this.sex = sex;
        this.createdAt = createdAt;
    }

    public UserDetails(long id,
                       long id_user,
                       String surname,
                       LocalDate dateOfBirth,
                       String residence,
                       String profilePhotoSource,
                       String bgPhotoSource,
                       Sex sex,
                       LocalDate createdAt) {
        this.id = id;
        this.id_user = id_user;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.residence = residence;
        this.profilePhotoSource = profilePhotoSource;
        this.bgPhotoSource = bgPhotoSource;
        this.sex = sex;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
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
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", residence='" + residence + '\'' +
                ", profilePhotoSource='" + profilePhotoSource + '\'' +
                ", bgPhotoSource='" + bgPhotoSource + '\'' +
                ", sex=" + sex +
                ", createdAt=" + createdAt +
                '}';
    }
}
