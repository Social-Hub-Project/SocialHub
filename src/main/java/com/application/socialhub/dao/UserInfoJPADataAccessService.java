package com.application.socialhub.dao;

import com.application.socialhub.model.UserInfo;
import com.application.socialhub.repository.UserInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userInfoJpa")
public class UserInfoJPADataAccessService implements UserInfoDAO{
    private final UserInfoRepository repository;

    public UserInfoJPADataAccessService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserInfo> findUserInfoByEmail(String email) {
        return Optional.ofNullable(repository.findUserInfoByEmail(email));
    }
}
