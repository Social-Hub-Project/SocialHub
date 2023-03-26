package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.dtoMappers.UserDetailsDTOMapper;
import com.application.socialhub.exception.DuplicateResourceException;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserDetailsDTOMapper userDetailsDTOMapper;
    private final UserDTOMapper userDTOMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Qualifier("jdbc") UserDAO userDAO,
                       UserDetailsDTOMapper userDetailsDTOMapper,
                       UserDTOMapper userDTOMapper,
                       PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.userDetailsDTOMapper = userDetailsDTOMapper;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {

        return userDAO.selectAllUsers()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public void addUser(UserRegistrationRequest request) {

            String email = request.email();
            //TODO create method below and passwordEncoder
            if (userDAO.existsUserWithEmail(email)) {
                throw new DuplicateResourceException(
                        "email already taken"
                );
            }


            User user = new User(
                    Role.USER,
                    request.email(),
                    request.name(),
                    passwordEncoder.encode(request.password()),
                    true,
                    LocalDate.now().toString()
                    );


        userDAO.insertUser(user);

    }
}
