package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dao.UserInfoDAO;
import com.application.socialhub.dto.DefaultGetRequest;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserDetailsDTO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.dtoMappers.UserEntityDTOMapper;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import com.application.socialhub.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserInfoDAO userInfoDAO;
    private final UserDTOMapper userDTOMapper;
    private final UserEntityDTOMapper userEntityDTOMapper;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserService(@Qualifier("jpa") UserDAO userDAO,
                       @Qualifier("userInfoJpa") UserInfoDAO userInfoDAO,
                       UserDTOMapper userDTOMapper,
                       UserEntityDTOMapper userEntityDTOMapper,
                       ConfirmationTokenService confirmationTokenService, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userDAO = userDAO;
        this.userDTOMapper = userDTOMapper;
        this.userEntityDTOMapper = userEntityDTOMapper;
        this.confirmationTokenService = confirmationTokenService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userInfoDAO = userInfoDAO;
    }

    public List<UserDTO> getAllUsers() {

        return userDAO.selectAllUsers()
                .stream()
                .map(userEntityDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDetailsDTO getUserInfo(DefaultGetRequest request){
        String email = jwtUtil.getSubject(request.token());
        UserInfo user = userInfoDAO.findUserInfoByEmail(email);
        return new UserDTOMapper().apply(user);
    }

    public void addUser(UserRegistrationRequest request) {
    }
}
