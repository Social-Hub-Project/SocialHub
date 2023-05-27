package com.application.socialhub.dao;

import com.application.socialhub.model.UserInfo;
import com.application.socialhub.repository.UserInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userInfoJpa")
public class UserInfoJPADataAccessService implements UserInfoDAO{
    private final UserInfoRepository repository;

    public UserInfoJPADataAccessService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserInfo findUserInfoByEmail(String email) {
        return repository.findUserInfoByEmail(email);
    }

    @Override
    public void changeProfilePhoto(long id, String path) {
        repository.changeProfilePhoto(id, path);
    }

    @Override
    public void changeBackgroundPhoto(long id, String path) {
        repository.changeBackgroundPhoto(id, path);
    }

    @Override
    public List<UserInfo> findUser(String name) {
        return repository.findUser(name);
    }

    @Override
    public UserInfo findUserInfoById(long id) {
        return repository.findUserInfoById(id);
    }
}
