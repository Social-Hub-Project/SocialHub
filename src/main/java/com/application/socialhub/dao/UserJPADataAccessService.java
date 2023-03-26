package com.application.socialhub.dao;

import com.application.socialhub.model.User;
import com.application.socialhub.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class UserJPADataAccessService implements UserDAO{


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
