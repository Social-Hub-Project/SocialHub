package com.application.socialhub.dao;


import com.application.socialhub.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jdbc")
public class UserJDBCDataAccessService implements UserDAO{
    @Override
    public List<User> selectAllUsers() {
        return null;
    }
}
