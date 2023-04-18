package com.application.socialhub.dao;

import com.application.socialhub.model.UserInfo;

import java.util.Optional;

public interface UserInfoDAO {

    Optional<UserInfo> findUserInfoByEmail(String email);
}
