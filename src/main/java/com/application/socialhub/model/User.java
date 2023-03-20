package com.application.socialhub.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

public class User {

    private long id;

    private Role role;

    private Sex sex;

    private String email;

    private String password;

    private Boolean active;

    private String name;

    private String surname;

    private LocalDate birthday;

    private String residence;

    private Boolean blocked;

    private String profilePhotoSource;

    private String bgPhotoSource;

    private LocalDate createdAt;

    public User() {
    }

    public User(Role role,
                Sex sex,
                String email,
                String password,
                Boolean active,
                String name,
                String surname,
                LocalDate birthday,
                String residence,
                Boolean blocked,
                String profilePhotoSource,
                String bgPhotoSource) {
        this.role = role;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.active = active;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.residence = residence;
        this.blocked = blocked;
        this.profilePhotoSource = profilePhotoSource;
        this.bgPhotoSource = bgPhotoSource;
    }

    public User(Long id,
                Role role,
                Sex sex,
                String email,
                String password,
                Boolean active,
                String name,
                String surname,
                LocalDate birthday,
                String residence,
                Boolean blocked,
                String profilePhotoSource,
                String bgPhotoSource) {
        this.id = id;
        this.role = role;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.active = active;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.residence = residence;
        this.blocked = blocked;
        this.profilePhotoSource = profilePhotoSource;
        this.bgPhotoSource = bgPhotoSource;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", residence='" + residence + '\'' +
                ", blocked=" + blocked +
                ", profilePhotoSource='" + profilePhotoSource + '\'' +
                ", bgPhotoSource='" + bgPhotoSource + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
