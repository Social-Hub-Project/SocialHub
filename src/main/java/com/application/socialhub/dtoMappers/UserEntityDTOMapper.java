package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.UserDTO;
import com.application.socialhub.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserEntityDTOMapper implements Function<UserEntity, UserDTO> {

    @Override
    public UserDTO apply(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getEmail(),
                userEntity.getRole(),
                userEntity.getActive()
        );
    }
}
