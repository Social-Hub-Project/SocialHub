package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dao.UserInfoDAO;
import com.application.socialhub.dtoMappers.UserEntityDTOMapper;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.util.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserEntityServiceTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private UserInfoDAO userInfoDAO;
    @Mock
    private UserDTOMapper userDTOMapper;
    @Mock
    private UserEntityDTOMapper userEntityDTOMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JWTUtil jwtUtil;

    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userDAO,
                userInfoDAO,
                userDTOMapper,
                userEntityDTOMapper,
                passwordEncoder,
                jwtUtil);
    }

    @Test
    void canGetAllUsers() {
        // when
        underTest.getAllUsers();
        // then
        verify(userDAO).selectAllUsers();
    }

    @Test
    @Disabled
    void addUser() {
    }
}