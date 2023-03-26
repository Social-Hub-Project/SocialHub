package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.dtoMappers.UserDetailsDTOMapper;
import com.application.socialhub.exception.DuplicateResourceException;
import com.application.socialhub.model.ConfirmationToken;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserDetailsDTOMapper userDetailsDTOMapper;
    private final UserDTOMapper userDTOMapper;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Qualifier("jdbc") UserDAO userDAO,
                       UserDetailsDTOMapper userDetailsDTOMapper,
                       UserDTOMapper userDTOMapper,
                       ConfirmationTokenService confirmationTokenService, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.userDetailsDTOMapper = userDetailsDTOMapper;
        this.userDTOMapper = userDTOMapper;
        this.confirmationTokenService = confirmationTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {

        return userDAO.selectAllUsers()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public void addUser(UserRegistrationRequest request) {



    }
}
