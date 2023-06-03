package com.application.socialhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import java.time.LocalDate;
import java.util.*;

@Entity
@Table(
        name = "my_user"
)
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    @NotNull(message = "role field can't be null!")
    private Role role;
    @Column(
            nullable = false,
            unique = true
    )
    @Email(message = "wrong email!")
    @Pattern(regexp = ".+@.+\\..+", message = "wrong email pattern!")
    @NotBlank(message = "email field can't be blank!")
    @Size(min = 5, max = 64, message = "email field size is between 5 and 64 characters!")
    private String email;

    @Column(
            nullable = false
    )
    @NotBlank(message = "password field can't be blank!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–\\[\\]{}:;',?/*~$^+=<>]).{8,64}$",
            message = "Wrong password pattern!\n" +
                    "Provide at least 1 letter, 1 capital letter, 1 digit, 1 special char,\n" +
                    "password length is between 8 and 64 chars!\n ")
    private String password;
    @Column(
            nullable = false
    )
    @NotNull(message = "active field can't be null!")
    private Boolean active;
    @Column(
            nullable = false
    )
    @NotNull(message = "createdAt field can't be null!")
    private LocalDate createdAt;

    private Boolean enabled;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Post> posts;

    @OneToMany(mappedBy = "userEntity",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Followers> followers;

    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Followers> following;

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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
        return email;
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