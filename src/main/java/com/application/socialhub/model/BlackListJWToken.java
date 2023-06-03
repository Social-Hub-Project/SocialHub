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
    private Date expirationDate;

    public BlackListJWToken() {
    }
    public BlackListJWToken( String token, Date expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }
    public BlackListJWToken(Long id, String token, Date expirationDate) {
        this.id = id;
        this.token = token;
        this.expirationDate = expirationDate;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlackListJWToken that)) return false;
        return  Objects.equals(getToken(), that.getToken()) && Objects.equals(getExpirationDate(), that.getExpirationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getToken(), getExpirationDate());
    }
}
