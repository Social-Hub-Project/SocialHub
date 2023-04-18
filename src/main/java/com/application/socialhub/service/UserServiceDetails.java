package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserServiceDetails implements UserDetailsService {

    private final UserDAO userDAO;

    public UserServiceDetails(@Qualifier("jpa") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity =  userDAO.findUserByEmail(email);
        if(userEntity == null)
            throw (new UsernameNotFoundException("Email " + email + " not found"));
        return userEntity;
    }
}
