package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dao.UserInfoDAO;
import com.application.socialhub.dto.*;
import com.application.socialhub.dtoMappers.UserEntityDTOMapper;
import com.application.socialhub.dtoMappers.UserDTOMapper;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import com.application.socialhub.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final UserInfoDAO userInfoDAO;
    private final UserDTOMapper userDTOMapper;
    private final UserEntityDTOMapper userEntityDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserService(@Qualifier("jpa") UserDAO userDAO,
                       @Qualifier("userInfoJpa") UserInfoDAO userInfoDAO,
                       UserDTOMapper userDTOMapper,
                       UserEntityDTOMapper userEntityDTOMapper,
                       PasswordEncoder passwordEncoder,
                       JWTUtil jwtUtil) {
        this.userDAO = userDAO;
        this.userDTOMapper = userDTOMapper;
        this.userEntityDTOMapper = userEntityDTOMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userInfoDAO = userInfoDAO;
    }

    public List<UserDTO> getAllUsers() {

        return userDAO.selectAllUsers()
                .stream()
                .map(userEntityDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDetailsDTO getUserInfo(DefaultGetRequest request){
        String email = jwtUtil.getSubject(request.token());
        UserInfo user = userInfoDAO.findUserInfoByEmail(email);
        return new UserDTOMapper().apply(user);
    }

    public UserDetailsDTO getUserInfoById(UserInfoIdRequest request){
        UserInfo user = userInfoDAO.findUserInfoById(request.id());
        return new UserDTOMapper().apply(user);
    }

    public ResponseEntity<?> changeProfilePhoto(ChangePhotoRequest request) {
        return changePhoto(request, "profilePhoto");
    }

    public ResponseEntity<?> changeBackgroundPhoto(ChangePhotoRequest request) {
        return changePhoto(request, "backgroundPhoto");
    }

    private ResponseEntity<?> changePhoto(ChangePhotoRequest request, String bgOrProfilePhoto) {
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);
            UserInfo userInfo = userInfoDAO.findUserInfoByEmail(email);
            String src = saveImage(user, request, bgOrProfilePhoto);

            if(bgOrProfilePhoto.equals("profilePhoto")) {
                userInfo.setProfilePhotoSource(src);
                userInfoDAO.changeProfilePhoto(userInfo.getId(), src);
            }else {
                userInfo.setBgPhotoSource(src);
                userInfoDAO.changeBackgroundPhoto(userInfo.getId(), src);
            }
            return new ResponseEntity<>(userDTOMapper.apply(userInfo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Change "+bgOrProfilePhoto+" Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public String saveImage(UserEntity user, ChangePhotoRequest request, String bgOrProfilePhoto) throws Exception {
        Path fileNameAndPath = Paths.get("/uploads/" + user.getId() + "/"+bgOrProfilePhoto+"/", request.photo().getOriginalFilename());
        File file = new File("/uploads/" + user.getId() + "/"+bgOrProfilePhoto+"");

        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new Exception("Couldn't create directory: "+ file.getPath());
            }
        }
        Files.write(fileNameAndPath, request.photo().getBytes());
        return fileNameAndPath.toString();
    }

    public ResponseEntity<?> changePassword(ChangePasswordRequest request){
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);
            if(!request.newPassword().equals(request.newPasswordConfirm())){
                return new ResponseEntity<>("Passwords don't match",HttpStatus.BAD_REQUEST);
            }
            if(passwordEncoder.matches(request.newPassword(),user.getPassword())){
                return new ResponseEntity<>("New password can't be the same as the old one",HttpStatus.BAD_REQUEST);
            }
            userDAO.changePassword(user.getId(),passwordEncoder.encode(request.newPassword()));

            return new ResponseEntity<>("Password has been changed successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
