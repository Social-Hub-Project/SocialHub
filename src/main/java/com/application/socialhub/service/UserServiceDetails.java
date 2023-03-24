package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetails implements UserDetailsService {

    private final UserDAO userDAO;

    public UserServiceDetails(@Qualifier("jpa") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userDAO.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Email " + email + " not found"));
    }
}
