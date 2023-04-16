package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.dtoMappers.UserEntityDTOMapper;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserDTOMapper userDTOMapper;
    private final UserEntityDTOMapper userEntityDTOMapper;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Qualifier("jpa") UserDAO userDAO,
                       UserDTOMapper userDTOMapper,
                       UserEntityDTOMapper userEntityDTOMapper,
                       ConfirmationTokenService confirmationTokenService, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.userDTOMapper = userDTOMapper;
        this.userEntityDTOMapper = userEntityDTOMapper;
        this.confirmationTokenService = confirmationTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {

        return userDAO.selectAllUsers()
                .stream()
                .map(userEntityDTOMapper)
                .collect(Collectors.toList());
    }

    public void addUser(UserRegistrationRequest request) {
    }
}
