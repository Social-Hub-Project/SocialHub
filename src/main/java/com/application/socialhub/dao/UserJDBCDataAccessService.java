package com.application.socialhub.dao;


import com.application.socialhub.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class UserJDBCDataAccessService implements UserDAO{
    @Override
    public List<User> selectAllUsers() {
        return null;
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return false;
    }

    @Override
    public void insertUser(User user) {

    }
}
