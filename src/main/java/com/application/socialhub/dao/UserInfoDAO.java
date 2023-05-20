package com.application.socialhub.dao;

import com.application.socialhub.dto.BasicUserInfoDTO;
import com.application.socialhub.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoDAO {

    UserInfo findUserInfoByEmail(String email);
    void changeProfilePhoto(long id, String path);
    void changeBackgroundPhoto(long id, String path);

    List<UserInfo> findUser(String name);

    UserInfo findUserInfoById(long id);
}
