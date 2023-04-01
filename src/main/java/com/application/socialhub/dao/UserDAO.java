package com.application.socialhub.dao;

import com.application.socialhub.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> selectAllUsers();

    Optional<User> findUserByEmail(String email);

    boolean existsUserWithEmail(String email);

    void insertUser(User user);

    void save(User user);

    void enableUser(String email);

    boolean selectUserEnabled(String email);

    boolean selectExistsEmail(String email);
}
