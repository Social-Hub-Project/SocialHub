package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.UserDetailsDTO;
import com.application.socialhub.model.UserInfo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.function.Function;
@Service
public class UserDTOMapper implements Function<UserInfo, UserDetailsDTO> {
    @Override
    public UserDetailsDTO apply(UserInfo userInfo) {
        try {
            return new UserDetailsDTO(
                    userInfo.getId(),
                    userInfo.getName(),
                    userInfo.getSurname(),
                    userInfo.getDateOfBirth(),
                    userInfo.getResidence(),
                    userInfo.getSex(),
                    userInfo.getCreatedAt(),
                    convertImagePathToImage(userInfo.getProfilePhotoSource()),
                    convertImagePathToImage(userInfo.getBgPhotoSource()),
                    userInfo.getProfilePhotoSource(),
                    userInfo.getBgPhotoSource(),
                    true
            );
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Blob convertImagePathToImage(String path) throws IOException, SQLException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        return new SerialBlob( new InputStreamResource(inputStream).getContentAsByteArray());
    }
}
