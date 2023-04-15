package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.UserDetailsDTO;
import com.application.socialhub.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDTOMapper implements Function<UserInfo, UserDetailsDTO> {
    @Override
    public UserDetailsDTO apply(UserInfo userInfo) {
        return new UserDetailsDTO(
                userInfo.getSurname(),
                userInfo.getDateOfBirth(),
                userInfo.getResidence(),
                userInfo.getSex()
        );
    }
}
