package com.application.socialhub.service;

import com.application.socialhub.dao.*;
import com.application.socialhub.dto.AllPostsForUserRequest;
import com.application.socialhub.dto.DeletePostRequest;
import com.application.socialhub.dto.UserInfoIdRequest;
import com.application.socialhub.model.Post;
import com.application.socialhub.model.PostWithCommentsAndRating;
import com.application.socialhub.model.PostsReturns;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDashboardService {

    private static final Logger logger = LoggerFactory.getLogger(UserDashboardService.class);
    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;
    private final CommentDAO commentDAO;
    private final PostCategoryDAO postCategoryDAO;

    private final RatingDAO ratingDAO;

    public UserDashboardService(@Qualifier("post")PostDAO postDAO,
                                @Qualifier("jpa")UserDAO userDAO, JWTUtil jwtUtil, @Qualifier("comment")CommentDAO commentDAO,
                                @Qualifier("postCategoryConnection") PostCategoryDAO postCategoryDAO,
                                @Qualifier("ratings") RatingDAO ratingDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
        this.commentDAO = commentDAO;
        this.postCategoryDAO = postCategoryDAO;
        this.ratingDAO = ratingDAO;
    }


    public ResponseEntity<?> deletePost(DeletePostRequest request) {
        try{
            String email=jwtUtil.getSubject(request.token());
            UserEntity owner=userDAO.findUserByEmail(email);
            Post postDeleted=postDAO.findPostById(request.postId());
            if(postDeleted==null){
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }
            if(postDeleted.getUserEntity().getId()!=owner.getId()){
                return new ResponseEntity<>("You are not the owner of this post", HttpStatus.UNAUTHORIZED);
            }
            ratingDAO.deleteRatingByPostId(postDeleted.getId());
            postCategoryDAO.deleteCategoryConnection(postDeleted.getId());
            commentDAO.deleteComment(postDeleted.getId());
            postDAO.deletePost(postDeleted);

            return new ResponseEntity<>("Post deleted successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> blockComments(DeletePostRequest request){
        try {
            String email=jwtUtil.getSubject(request.token());
            UserEntity owner=userDAO.findUserByEmail(email);
            Post postBlocked=postDAO.findPostById(request.postId());
            if(postBlocked==null){
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }

            if(postBlocked.isBlocked()){
                return new ResponseEntity<>("Comments are already blocked", HttpStatus.BAD_REQUEST);
            }

            if(postBlocked.getUserEntity().getId()!=owner.getId()){
                return new ResponseEntity<>("You are not the owner of this post", HttpStatus.UNAUTHORIZED);
            }

            postDAO.blcokPost(true, postBlocked.getId());
            return new ResponseEntity<>(" Adding comments blocked successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> unlockComments(DeletePostRequest request){
        try {
            String email=jwtUtil.getSubject(request.token());
            UserEntity owner=userDAO.findUserByEmail(email);
            Post postBlocked=postDAO.findPostById(request.postId());
            if(postBlocked==null){
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }

            if(postBlocked.isBlocked()){

                if(postBlocked.getUserEntity().getId()!=owner.getId()){
                    return new ResponseEntity<>("You are not the owner of this post", HttpStatus.UNAUTHORIZED);
                }

                postDAO.blcokPost(false, postBlocked.getId());
                return new ResponseEntity<>(" Adding comments unlock successfully",HttpStatus.OK);
            }

            return new ResponseEntity<>("Commenting was not blocked", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> getAllPostsForUser(AllPostsForUserRequest request) {
        try {
            List<Post> posts = postDAO.findAllPostsOrderedByCreatedAtDescForDedUser(request.userId());
            List<PostWithCommentsAndRating> postWithCommentsAndRatingsList = new ArrayList<>();
            logger.warn("dupa");
            for (Post post : posts) {
                List<PostsReturns> comments = commentDAO.findCommentsByPostId(post.getId());
                int likes = ratingDAO.findPostLikes(post.getId());
                int dislikes = ratingDAO.findPostDislikes(post.getId());
                String email=jwtUtil.getSubject(request.token());
                logger.warn(email);
                UserEntity ratingsUser=userDAO.findUserByEmail(email);
                Integer lickedByUser=ratingDAO.ratingUser(ratingsUser.getId(),post.getId());

                // post images
                File file = new File(post.getPhoto_source());
                InputStream inputStream = new FileInputStream(file);
                Blob imageBlob = new SerialBlob(new InputStreamResource(inputStream).getContentAsByteArray());

                // comments images
                List<Blob> imagesCommentary = new ArrayList<>();
                for (PostsReturns comment : comments) {
                    try {
                        File file2 = new File(comment.getUser_entity_id().getUserInfo().getProfilePhotoSource());
                        InputStream inputStream2 = new FileInputStream(file2);
                        Blob imageBlob2 = new SerialBlob(new InputStreamResource(inputStream2).getContentAsByteArray());

                        imagesCommentary.add(imageBlob2);
                    } catch (Exception e) {
                        // TODO: handle exception
                        imagesCommentary.add(null);
                    }

                }

                postWithCommentsAndRatingsList.add(new PostWithCommentsAndRating(post, comments, likes, dislikes,lickedByUser,
                        imageBlob, imagesCommentary));

            }

            return new ResponseEntity<>(postWithCommentsAndRatingsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
