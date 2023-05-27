package com.application.socialhub.dao;

import com.application.socialhub.model.UserEntity;

import java.util.List;

public interface UserDAO {
    List<UserEntity> selectAllUsers();

    UserEntity findUserByEmail(String email);

    boolean existsUserWithEmail(String email);

    void save(UserEntity userEntity);

    void enableUser(String email);

    boolean selectUserEnabled(String email);

    boolean selectExistsEmail(String email);

    void updateUserState(boolean state, String email);

    UserEntity findUserById(long id);

    void changePassword(long userId, String newPassword);
}
