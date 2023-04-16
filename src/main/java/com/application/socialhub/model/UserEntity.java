package com.application.socialhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class UserEntity implements UserDetails {

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


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,
            name = "id_user_info",
        unique = true)
    private UserInfo userInfo;

    public UserEntity(long id, Role role, String email, String password, Boolean active, LocalDate createdAt, Boolean enabled, UserInfo userInfo) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
        this.active = active;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.userInfo = userInfo;
    }

    public UserEntity(Role role, String email, String password, Boolean active, LocalDate createdAt, Boolean enabled, UserInfo userInfo) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.active = active;
        this.createdAt = createdAt;
        this.enabled = enabled;
        this.userInfo = userInfo;
    }


    public UserEntity() {
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null) return false;
        if (getClass() != o.getClass()) return false;
        UserEntity other = (UserEntity) o;
        return getId() == other.getId() &&
                getRole() == other.getRole() &&
                Objects.equals(getEmail(), other.getEmail()) &&
                Objects.equals(getPassword(), other.getPassword()) &&
                Objects.equals(getActive(), other.getActive()) &&
                Objects.equals(getCreatedAt(), other.getCreatedAt()) &&
                Objects.equals(isEnabled(), other.isEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole(), getEmail(), getPassword(), getActive(), getCreatedAt(), isEnabled());
    }
}
