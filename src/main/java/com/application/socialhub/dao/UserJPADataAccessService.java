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
    public UserEntity findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
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

    @Override
    public void updateUserState(boolean state, String email) {
        userRepository.updateUserState(state, email);
    }

    @Override
    public UserEntity findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void changePassword(long userId, String newPassword) {
        userRepository.changePassword(userId, newPassword);
    }

}
