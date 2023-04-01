package com.application.socialhub.service;

import com.application.socialhub.controller.AuthenticationController;
import com.application.socialhub.dto.AuthenticationRequest;
import com.application.socialhub.dto.AuthenticationResponse;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.model.User;
import com.application.socialhub.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDTOMapper userDTOMapper;
    private final JWTUtil jwtUtil;
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserDTOMapper userDTOMapper,
                                 JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDTOMapper = userDTOMapper;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        logger.info("authentication " + authentication);

        User principal = (User) authentication.getPrincipal();
        logger.info("principal " + principal);

        UserDTO userDTO = userDTOMapper.apply(principal);
        String token = jwtUtil.issueToken(userDTO.email(), userDTO.role().toString());
        AuthenticationResponse response  = new AuthenticationResponse(token, userDTO);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}
