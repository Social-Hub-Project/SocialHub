package com.application.socialhub.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "user_details"
)
public class UserInfo {
    @Id
    @SequenceGenerator(
            name = "my_user_det_id_seq",
            sequenceName = "my_user_det_id_seq",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_user_det_id_seq"
    )
    private long id;
    @Column(
            nullable = false
    )
    private String name;
    @Column(
            nullable = false
    )
    private String surname;
    @Column(
            nullable = false
    )
    private LocalDate dateOfBirth;
    @Column(
            nullable = false
    )
    private String residence;
    @Column(
            nullable = false
    )
    private boolean blocked;
    @Column(
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String profilePhotoSource;
    @Column(
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String bgPhotoSource;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(
            nullable = false
    )
    private LocalDate createdAt;



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
//                ", user=" + userEntity +
                '}';
    }
}
