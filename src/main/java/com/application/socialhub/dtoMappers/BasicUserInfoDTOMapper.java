package com.application.socialhub.dtoMappers;

import com.application.socialhub.dao.FollowerDAO;
import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.BasicUserInfoDTO;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final FollowerDAO followerDAO;
    private final String email;

    private final UserDAO userDAO;

    public BasicUserInfoDTOMapper(@Qualifier("follower") FollowerDAO followerDAO,@Qualifier("jpa") UserDAO userDAO,String email) {
        this.email = email;
        this.userDAO = userDAO;
        this.followerDAO = followerDAO;
    }


    @Override
    public BasicUserInfoDTO apply(UserInfo userInfo) {

        boolean isFriend = isUserAFriend(userInfo);

        try {
            return new BasicUserInfoDTO(userInfo.getName(),
                    userInfo.getSurname(),
                    userInfo.getId(),
                    convertImagePathToImage(userInfo.getProfilePhotoSource()),
                    isFriend);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Blob convertImagePathToImage(String path) throws IOException, SQLException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        return new SerialBlob( new InputStreamResource(inputStream).getContentAsByteArray());
    }

    private boolean isUserAFriend(UserInfo userInfo){
        UserEntity user = userDAO.findUserByEmail(email);
        return followerDAO.checkIfFollowerExists(user.getId(),userInfo.getId());
    }
}


