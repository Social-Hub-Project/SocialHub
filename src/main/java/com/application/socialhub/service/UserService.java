package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.dtoMappers.UserDetailsDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserDetailsDTOMapper userDetailsDTOMapper;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserService(@Qualifier("jpa") UserDAO userDAO, UserDetailsDTOMapper userDetailsDTOMapper, UserDTOMapper userDTOMapper) {
        this.userDAO = userDAO;
        this.userDetailsDTOMapper = userDetailsDTOMapper;
        this.userDTOMapper = userDTOMapper;
    }

    public List<UserDTO> getAllUsers() {

        return userDAO.selectAllUsers()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }
}
