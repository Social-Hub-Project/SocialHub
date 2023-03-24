package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.UserDetailsDTO;
import com.application.socialhub.model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDetailsDTOMapper implements Function<UserDetails, UserDetailsDTO> {
    @Override
    public UserDetailsDTO apply(UserDetails userDetails) {
        return new UserDetailsDTO(
                userDetails.getSurname(),
                userDetails.getDateOfBirth(),
                userDetails.getResidence(),
                userDetails.getSex()
        );
    }
}
