package com.application.socialhub.service;

import com.application.socialhub.dao.CommentDAO;
import com.application.socialhub.dao.PostDAO;
import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.CreateCommentRequest;
import com.application.socialhub.dto.CreatePostRequest;
import com.application.socialhub.model.Comment;
import com.application.socialhub.model.Post;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.util.JWTUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class MainPageService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;
    private CommentDAO commentDAO;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";


    public MainPageService(@Qualifier("post") PostDAO postDAO,
                           @Qualifier("jpa") UserDAO userDAO,
                           @Qualifier("comment") CommentDAO commentDAO,
                           JWTUtil jwtUtil) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
    }


    public ResponseEntity<?> createPost(CreatePostRequest request) {
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);

            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, request.image().getOriginalFilename());
            fileNames.append(request.image().getOriginalFilename());
            Files.write(fileNameAndPath, request.image().getBytes());

            Post newPost = new Post(request.description(), false, LocalDate.now(), fileNameAndPath.toString(), user);

            postDAO.savePost(newPost);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllPosts() {
        try {
            List<Post> posts = postDAO.findAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> commentPost(CreateCommentRequest request){
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail("marek@email.com");
            Post postCommented = postDAO.findPostById(request.idPost());
            if(postCommented == null) return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);

            Comment comment = new Comment(request.description(), LocalDate.now(), user, postCommented);

            commentDAO.saveComment(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
