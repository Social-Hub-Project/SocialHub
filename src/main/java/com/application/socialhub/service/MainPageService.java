package com.application.socialhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.application.socialhub.dao.CommentDAO;
import com.application.socialhub.dao.PostDAO;
import com.application.socialhub.dao.RatingDAO;
import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.CreateCommentRequest;
import com.application.socialhub.dto.CreatePostRequest;
import com.application.socialhub.dto.CreateRatingRequest;
import com.application.socialhub.dto.PostWithImageDTO;
import com.application.socialhub.model.Comment;
import com.application.socialhub.model.Post;
import com.application.socialhub.model.Rating;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.util.JWTUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.serial.SerialBlob;


@Service
public class MainPageService {
    private static final Logger logger = LoggerFactory.getLogger(MainPageService.class);

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;

    private final RatingDAO ratingDAO;
    private CommentDAO commentDAO;

    public MainPageService(@Qualifier("post") PostDAO postDAO,
            @Qualifier("jpa") UserDAO userDAO,
            @Qualifier("comment") CommentDAO commentDAO,
            @Qualifier("ratings") RatingDAO ratingDAO,
            JWTUtil jwtUtil) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
        this.commentDAO = commentDAO;
        this.ratingDAO = ratingDAO;
    }

    private static final Logger logger = LoggerFactory.getLogger(MainPageService.class);

    public ResponseEntity<?> createPost(CreatePostRequest request) {
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);

            logger.warn(request.image().toString());
            logger.warn(request.image().getOriginalFilename());

            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get("/uploads/" + user.getId() + "/", request.image().getOriginalFilename());
            fileNames.append(request.image().getOriginalFilename());
            File file = new File("/uploads/" + user.getId() + "");

            if (!file.exists()) {

                if (!file.mkdirs()) {
                    throw new Exception("Couldn't create directory: "+ file.getPath()
                }
            }

            Files.write(fileNameAndPath, request.image().getBytes());
            Post newPost = new Post(request.description(), false, LocalDate.now(), fileNameAndPath.toString(), user);
            File file2 = new File(newPost.getPhoto_source());
            InputStream inputStream = new FileInputStream(file2);
            Blob imageBlob = new SerialBlob( new InputStreamResource(inputStream).getContentAsByteArray());
            PostWithImageDTO postWithImage = new PostWithImageDTO(imageBlob ,newPost);
            postDAO.savePost(newPost);

            return new ResponseEntity<>(postWithImage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllPosts() {
        try {
            List<Post> posts = postDAO.findAllPosts();
            List<PostWithImageDTO> imagesAndPosts = new ArrayList<>();
            for(Post p: posts) {

               File file = new File(p.getPhoto_source());
               InputStream inputStream = new FileInputStream(file);
               Blob imageBlob = new SerialBlob( new InputStreamResource(inputStream).getContentAsByteArray());
               imagesAndPosts.add(new PostWithImageDTO(imageBlob, p));

            }

            return new ResponseEntity<>(imagesAndPosts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> commentPost(CreateCommentRequest request) {
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);
            Post postCommented = postDAO.findPostById(request.idPost());

            if (postCommented == null)
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);

            if (postCommented.isBlocked())
                return new ResponseEntity<>("Post is blocked", HttpStatus.UNAUTHORIZED);

            Comment comment = new Comment(request.description(), LocalDate.now(), user, postCommented);

            commentDAO.saveComment(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> ratingPost(CreateRatingRequest request) {
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);
            Post postRated = postDAO.findPostById(request.idPost());

            if (postRated == null)
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);

            // if rating already exists

            Rating existingRating = ratingDAO.checkRating(user.getId(), postRated.getId());

            if (existingRating == null) {
                // the user hasn't rated this post yet, so we're creating a new rating
                if (request.like() != 0) {
                    Rating newRating = new Rating(request.like(), LocalDate.now(), LocalDate.now(), user, postRated);
                    ratingDAO.saveRating(newRating);
                }

            } else {
                // the user has already rated this post, so we're updating his rating
                if (request.like() == 0) {
                    ratingDAO.deleteRating(existingRating);
                } else {
                    // the rating has been changed, so we update it in the database
                    existingRating.setValue(request.like());
                    ratingDAO.saveRating(existingRating);
                }
            }

            return new ResponseEntity<>(existingRating, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
