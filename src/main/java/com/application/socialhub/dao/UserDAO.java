package com.application.socialhub.dao;

import com.application.socialhub.model.User;

import java.util.List;

public interface UserDAO {
    List<User> selectAllUsers();
}
