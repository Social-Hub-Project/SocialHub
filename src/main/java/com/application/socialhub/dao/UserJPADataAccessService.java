package com.application.socialhub.dao;

import com.application.socialhub.model.User;
import com.application.socialhub.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
public class UserJPADataAccessService implements UserDAO{

    private final UserRepository userRepository;

    public UserJPADataAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> selectAllUsers() {
        Page<User> page = userRepository.findAll(Pageable.ofSize(1000));
        return page.getContent();
    }
}
