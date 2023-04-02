package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getEmail(),
                user.getRole(),
                user.getActive()
        );
    }
}
