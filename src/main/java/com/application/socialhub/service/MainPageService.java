package com.application.socialhub.service;

import com.application.socialhub.dao.PostDAO;
import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.CreatePostRequest;
import com.application.socialhub.model.Post;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.util.JWTUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MainPageService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;

    public MainPageService(@Qualifier("post") PostDAO postDAO,
                           @Qualifier("jpa") UserDAO userDAO,
                           JWTUtil jwtUtil) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
    }


    public ResponseEntity<?> createPost(CreatePostRequest request) {
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);
            Post newPost = new Post(request.description(), false, LocalDate.now(), request.photo(), user);
            //TODO find error source

            //TODO query to DB - add post
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getPosts() {
        try {
            List<Post> posts = postDAO.findAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}