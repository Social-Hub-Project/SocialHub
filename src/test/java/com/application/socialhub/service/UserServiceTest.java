package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.dtoMappers.UserDetailsDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private UserDetailsDTOMapper userDetailsDTOMapper;
    @Mock
    private UserDTOMapper userDTOMapper;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userDAO,
                userDetailsDTOMapper,
                userDTOMapper,
                confirmationTokenService,
                passwordEncoder);
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