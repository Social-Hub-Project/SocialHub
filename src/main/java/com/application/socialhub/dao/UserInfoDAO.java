package com.application.socialhub.dao;

import com.application.socialhub.model.UserInfo;

import java.util.Optional;

public interface UserInfoDAO {

    UserInfo findUserInfoByEmail(String email);
    void changeProfilePhoto(long id, String path);
    void changeBackgroundPhoto(long id, String path);

}
