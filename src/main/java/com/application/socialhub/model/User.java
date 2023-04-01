package com.application.socialhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

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
    private String name;

    @Column(
            nullable = false
    )
    private String password;

    private Boolean active;
    private String createdAt;

    private Boolean enabled;

    private Boolean locked;

    public User() {
    }

    public User( Role role,
                 String email,
                 String name,
                 String password,
                 String createdAt
                ) {
        this.role = role;
        this.email = email;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.active = false;
        this.enabled = false;
        this.locked = false;
    }

    public User(long id,
                Role role,
                String email,
                String name,
                String password,
                String createdAt
                ) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.name = name;
        this.password = password;
        this.active = false;
        this.createdAt = createdAt;
        this.enabled = false;
        this.locked = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
        // email because we use "public UserDetails loadUserByUsername(String email)"
        // this method will search for getUsername() in DB, but we compare emails not names.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
