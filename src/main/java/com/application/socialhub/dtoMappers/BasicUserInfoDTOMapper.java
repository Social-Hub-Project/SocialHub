package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.BasicUserInfoDTO;
import com.application.socialhub.model.UserInfo;
import org.springframework.core.io.InputStreamResource;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.function.Function;

public class BasicUserInfoDTOMapper implements Function<UserInfo, BasicUserInfoDTO> {

    @Override
    public BasicUserInfoDTO apply(UserInfo userInfo) {
        try {
            return new BasicUserInfoDTO(userInfo.getName(),
                    userInfo.getSurname(),
                    userInfo.getId(),
                    convertImagePathToImage(userInfo.getProfilePhotoSource()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Blob convertImagePathToImage(String path) throws IOException, SQLException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        return new SerialBlob( new InputStreamResource(inputStream).getContentAsByteArray());
    }
}


