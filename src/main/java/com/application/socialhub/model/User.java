package com.application.socialhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
@Table(
        name = "my_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "my_user_email_unique",
                        columnNames = "email"
                )
        }
)
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "my_user_id_seq",
            sequenceName = "my_user_id_seq",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "my_user_id_seq"
    )
    private long id;

    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(
            nullable = false
    )
    private String email;

    @Column(
            nullable = false
    )
    private String password;
    @Column(
            nullable = false
    )
    private Boolean active;
    @Column(
            nullable = false
    )
    private LocalDate createdAt;

    private Boolean enabled;

    public User(long id, Role role, String email, String password, Boolean active, Boolean enabled, LocalDate createdAt) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.active = active;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public User(Role role, String email, String password, Boolean active, LocalDate createdAt) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.active = active;
        this.createdAt = createdAt;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", createdAt=" + createdAt +
                '}';
    }
}
