package com.application.socialhub.service;

import com.application.socialhub.controller.AuthenticationController;
import com.application.socialhub.dto.AuthenticationRequest;
import com.application.socialhub.dto.AuthenticationResponse;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dtoMappers.UserEntityDTOMapper;
import com.application.socialhub.exception.AuthenticationFailedException;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserEntityDTOMapper userEntityDTOMapper,
                                 JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userEntityDTOMapper = userEntityDTOMapper;
        this.jwtUtil = jwtUtil;
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
            AuthenticationResponse response = new AuthenticationResponse(token, userDTO);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new AuthenticationFailedException(request.email(), request.password());
        }
    }

}
