package com.application.socialhub.service;

import com.application.socialhub.dto.AuthenticationRequest;
import com.application.socialhub.dto.AuthenticationResponse;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.exception.AuthenticationFailedException;
import com.application.socialhub.model.User;
import com.application.socialhub.util.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.time.LocalDate;

import static java.time.Month.MARCH;
import static org.mockito.Mockito.*;
import static com.application.socialhub.model.Role.USER;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDTOMapper userDTOMapper;
    @Mock
    private JWTUtil jwtUtil;

    private AuthenticationService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AuthenticationService(authenticationManager, userDTOMapper, jwtUtil);
    }

    @Test
    void loginShouldThrowAuthenticationException() {
        // Given
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        AuthenticationException authenticationException = new AuthenticationException("test-exception") {};
        when(authenticationManager.authenticate(any())).thenThrow(authenticationException);

        // When
        // Then
        assertThrows(AuthenticationFailedException.class, () -> underTest.login(request));
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    void shouldLoginSuccessfully() {
        // Given

        AuthenticationRequest request = new AuthenticationRequest("test@test.com", "test123");

        User user = new User(USER,"test@test.com", "Test", true,LocalDate.of(2001, MARCH, 14));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));

        when(userDTOMapper.apply(any(User.class))).thenReturn(new UserDTO("test@test.com", USER, true));

        String token = "test.token.string";
        when(jwtUtil.issueToken(anyString(), anyString())).thenReturn(token);
        // When

        ResponseEntity<?> response = underTest.login(request);
        // Then

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDTOMapper).apply(any(User.class));
        verify(jwtUtil).issueToken(anyString(), anyString());

        AuthenticationResponse expectedResponse = new AuthenticationResponse(token, new UserDTO("test@test.com", USER, true));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse.toString(), response.getBody());
    }

    @Test
    void testLoginInvalidCredentials() {
        // Given

        AuthenticationRequest request = new AuthenticationRequest("test@test.com", "test123");
        // When

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Then

        assertThrows(AuthenticationFailedException.class, () -> {
            underTest.login(request);
        });
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(userDTOMapper, jwtUtil);
    }

    @Test
    void testLoginNullRequest() {
        // Given
        // When
        // Then
        assertThrows(NullPointerException.class, () -> underTest.login(null));

        verifyNoInteractions(authenticationManager, userDTOMapper, jwtUtil);
    }

}