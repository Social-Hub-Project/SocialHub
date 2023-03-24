package com.application.socialhub.dao;

import com.application.socialhub.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> selectAllUsers();
    Optional<User> selectUserByEmail(String email);
}
