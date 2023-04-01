package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.exception.DuplicateResourceException;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.Sex;
import com.application.socialhub.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static com.application.socialhub.model.Sex.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private EmailValidatorService emailValidator;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @Mock
    private UserDAO userDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailSender emailSender;

    private RegistrationService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RegistrationService(userDAO,
                emailValidator,
                confirmationTokenService,
                passwordEncoder,
                emailSender);
    }

    @Test
    void doesRegisterCreateUser() {

        // given
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Dominik",
                "Kowal",
                "dkowal@gmail.com",
                "password",
                MALE,
                "Kraków",
                "12-02-2000"
        );

        User user = new User(Role.USER,
                "dkowal@gmail.com",
                "Dominik",
                passwordEncoder.encode("password"),
                LocalDate.now().toString());

        given(emailValidator.test(anyString()))
                .willReturn(true);

        given(userDAO.existsUserWithEmail(anyString()))
                .willReturn(false);

        // when
        underTest.register(request);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userDAO).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertTrue( capturedUser.equals(user));
    }

    @Test
    @Disabled
    void willThrowWhenEmailIsNotValid() {
        //TODO

        // given

        // when

        // then
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Dominik",
                "Kowal",
                "dkowal@gmail.com",
                "password",
                MALE,
                "Kraków",
                "12-02-2000"
        );
        given(emailValidator.test(anyString()))
                .willReturn(true);

        given(userDAO.existsUserWithEmail(anyString()))
                .willReturn(true);

        // when
        // then
        assertThatThrownBy(() -> underTest.register(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("email" + request.email() +" already taken");

        verify(userDAO, never()).save(any());
    }
}