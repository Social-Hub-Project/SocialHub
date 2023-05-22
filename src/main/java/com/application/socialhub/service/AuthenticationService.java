package com.application.socialhub.service;

import com.application.socialhub.dao.UserInfoDAO;
import com.application.socialhub.dto.AuthenticationFailedResponse;
import com.application.socialhub.dto.AuthenticationRequest;
import com.application.socialhub.dto.AuthenticationResponse;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.dtoMappers.UserEntityDTOMapper;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import com.application.socialhub.util.JWTUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserEntityDTOMapper userEntityDTOMapper;
    private final UserInfoDAO userInfoDAO;
    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserEntityDTOMapper userEntityDTOMapper,
                                 @Qualifier("userInfoJpa") UserInfoDAO userInfoDAO,
                                 JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userEntityDTOMapper = userEntityDTOMapper;
        this.jwtUtil = jwtUtil;
        this.userInfoDAO = userInfoDAO;
    }

    public ResponseEntity<?> login(AuthenticationRequest request) {
        try {
            Authentication authentication;

            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            UserEntity principal = (UserEntity) authentication.getPrincipal();
            UserDTO userDTO = userEntityDTOMapper.apply(principal);
            String token = jwtUtil.issueToken(userDTO.email(), userDTO.role().toString());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserInfo userInfo = userInfoDAO.findUserInfoByEmail(userDTO.email());

            return new ResponseEntity<>(new AuthenticationResponse(token,
                    new UserDTOMapper().apply(userInfo),
                    "Login success"), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new AuthenticationFailedResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
