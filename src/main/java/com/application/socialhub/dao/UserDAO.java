package com.application.socialhub.dao;

import com.application.socialhub.dto.UserDetailsDTO;
import com.application.socialhub.model.UserEntity;

import java.util.List;
import java.util.Optional;

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
}
