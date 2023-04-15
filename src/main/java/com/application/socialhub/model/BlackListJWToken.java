package com.application.socialhub.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(
        name = "black_list_token",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "black_list_token_unique",
                        columnNames = "token"
                )
        }
)
public class BlackListJWToken {


    @Id
    @SequenceGenerator(
            name = "black_token_id_seq",
            sequenceName = "black_token_id_seq",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "black_token_id_seq"
    )
    private Long id;

    @Column(nullable = false,
            name="token")
    private String token;

    @Column(nullable = false)
    private Date exprationDate;

    public BlackListJWToken() {
    }
    public BlackListJWToken( String token, Date exprationDate) {
        this.token = token;
        this.exprationDate = exprationDate;
    }
    public BlackListJWToken(Long id, String token, Date exprationDate) {
        this.id = id;
        this.token = token;
        this.exprationDate = exprationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExprationDate() {
        return exprationDate;
    }

    public void setExprationDate(Date exprationDate) {
        this.exprationDate = exprationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlackListJWToken that)) return false;
        return  Objects.equals(getToken(), that.getToken()) && Objects.equals(getExprationDate(), that.getExprationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getToken(), getExprationDate());
    }
}
