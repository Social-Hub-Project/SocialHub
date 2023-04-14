package com.application.socialhub.dao;

import com.application.socialhub.model.UserEntity;
import com.application.socialhub.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class UserJPADataAccessService implements UserDAO{

    private final UserRepository userRepository;

    public UserJPADataAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> selectAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email));
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return false;
    }

    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void enableUser(String email) {
         userRepository.enableUser(email);
    }

    @Override
    public boolean selectUserEnabled(String email) {
        return userRepository.selectUserEnabled(email);
    }

    @Override
    public boolean selectExistsEmail(String email) {
        return userRepository.selectExistsEmail(email);
    }

}
