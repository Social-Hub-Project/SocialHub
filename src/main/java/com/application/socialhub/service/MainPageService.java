package com.application.socialhub.service;

import com.application.socialhub.dao.*;
import com.application.socialhub.dto.*;
import com.application.socialhub.dtoMappers.BasicUserInfoDTOMapper;
import com.application.socialhub.dtoMappers.EventDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.application.socialhub.model.*;
import com.application.socialhub.util.JWTUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

@Service
public class MainPageService {
    private static final Logger logger = LoggerFactory.getLogger(MainPageService.class);

    private final PostDAO postDAO;
    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;

    private final RatingDAO ratingDAO;
    private final CommentDAO commentDAO;
    private final UserInfoDAO userInfoDAO;
    private final FollowerDAO followerDAO;
    private final EventDAO eventDAO;


    public MainPageService(@Qualifier("post") PostDAO postDAO,
                           @Qualifier("jpa") UserDAO userDAO,
                           @Qualifier("comment") CommentDAO commentDAO,
                           @Qualifier("ratings") RatingDAO ratingDAO,
                           @Qualifier("userInfoJpa") UserInfoDAO userInfoDAO,
                           @Qualifier("follower") FollowerDAO followerDAO,
                           @Qualifier("event") EventDAO eventDAO,
                           JWTUtil jwtUtil) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
        this.commentDAO = commentDAO;
        this.ratingDAO = ratingDAO;
        this.userInfoDAO = userInfoDAO;
        this.followerDAO = followerDAO;
        this.eventDAO = eventDAO;
    }

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
                    throw new Exception("Couldn't create directory: " + file.getPath());
                }
            }

            Files.write(fileNameAndPath, request.image().getBytes());
            Post newPost = new Post(request.description(), false, LocalDate.now(), fileNameAndPath.toString(), user);
            File file2 = new File(newPost.getPhoto_source());
            InputStream inputStream = new FileInputStream(file2);
            Blob imageBlob = new SerialBlob(new InputStreamResource(inputStream).getContentAsByteArray());
            PostWithImageDTO postWithImage = new PostWithImageDTO(imageBlob, newPost);
            postDAO.savePost(newPost);

            return new ResponseEntity<>(postWithImage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> getAllPosts(Authentication authentication) {
        try {
            List<Post> posts = postDAO.findPostByCreatedAt();
            List<PostWithCommentsAndRating> postWithCommentsAndRatingsList = new ArrayList<>();
            UserEntity currentUser = userDAO.findUserByEmail(authentication.getName());
            List<Long> frindsId= followerDAO.getFriendsId(currentUser.getId());
            for (Post post : posts) {
                if(frindsId.contains(post.getUserEntity().getId()) || post.getUserEntity().getId()==currentUser.getId()) {
                    List<PostsReturns> comments = commentDAO.findCommentsByPostId(post.getId());
                    int likes = ratingDAO.findPostLikes(post.getId());
                    int dislikes = ratingDAO.findPostDislikes(post.getId());
                    Integer lickedByUser = ratingDAO.ratingUser(currentUser.getId(), post.getId());

                    // post images
                    File file = new File(post.getPhoto_source());
                    InputStream inputStream = new FileInputStream(file);
                    Blob imageBlob = new SerialBlob(new InputStreamResource(inputStream).getContentAsByteArray());


                    File file2 = new File(post.getUserEntity().getUserInfo().getProfilePhotoSource());
                    InputStream inputStream2 = new FileInputStream(file2);
                    Blob creatorProfilePhoto = new SerialBlob(new InputStreamResource(inputStream2).getContentAsByteArray());

                    // comments images
                    List<Blob> imagesCommentary = new ArrayList<>();
                    for (PostsReturns comment : comments) {
                        try {
                            File file3 = new File(comment.getUser_entity_id().getUserInfo().getProfilePhotoSource());
                            InputStream inputStream3 = new FileInputStream(file3);
                            Blob imageBlob3 = new SerialBlob(new InputStreamResource(inputStream3).getContentAsByteArray());

                            imagesCommentary.add(imageBlob3);
                        } catch (Exception e) {
                            // TODO: handle exception
                            imagesCommentary.add(null);
                        }

                    }

                    postWithCommentsAndRatingsList.add(new PostWithCommentsAndRating(post, comments, likes, dislikes, lickedByUser,
                            imageBlob,creatorProfilePhoto ,imagesCommentary));
                }
            }

            return new ResponseEntity<>(postWithCommentsAndRatingsList, HttpStatus.OK);
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
            addNewEvent(postCommented.getUserEntity() ,user ," commented your post!");
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

            if(request.like() == 1) {
                addNewEvent(postRated.getUserEntity(),user," liked your post!");
            }else if(request.like() == -1){
                addNewEvent(postRated.getUserEntity(),user," disliked your post!");
            }
            return new ResponseEntity<>(existingRating, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    public ResponseEntity<?> searchUser(String name,Authentication authentication) {
        List<UserInfo> users = userInfoDAO.findUser(name);
        List<BasicUserInfoDTO> usersDTO = new LinkedList<>();
        BasicUserInfoDTOMapper mapper = new BasicUserInfoDTOMapper(followerDAO,userDAO,authentication.getName());
        for (UserInfo u: users) {
            usersDTO.add(mapper.apply(u));
        }
        return new ResponseEntity<>(usersDTO,HttpStatus.OK);
    }
    public ResponseEntity<?> followUser(FollowUserRequest request){
        try {
            String email = jwtUtil.getSubject(request.token());
            UserEntity user = userDAO.findUserByEmail(email);

            UserEntity userToFollow = userDAO.findUserById(request.userFollowingId());
            if(userToFollow==null)
                return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
            if(userToFollow.getId()==user.getId())
            {
                return new ResponseEntity<>("You can't follow yourself",HttpStatus.BAD_REQUEST);
            }
            if(followerDAO.checkIfFollowerExists(user.getId(),userToFollow.getId()))
            {
                return new ResponseEntity<>("You are already following this user",HttpStatus.BAD_REQUEST);
            }
            Followers followers = new Followers(LocalDate.now(),user,userToFollow);
            addNewEvent(userToFollow,user, " started following you!");
            followerDAO.addFollower(followers);
            return new ResponseEntity<>("User added to following",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> unfollowUser(FollowUserRequest request){
        try {
            String email=jwtUtil.getSubject(request.token());
            UserEntity follower = userDAO.findUserByEmail(email);
            UserEntity following = userDAO.findUserById(request.userFollowingId());
            if(follower==null || following==null){
                return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
            }
            if(followerDAO.checkIfFollowerExists(follower.getId(),following.getId())){
                Followers followersGroup=followerDAO.findFollowers(follower.getId(),following.getId());
                followerDAO.deleteFollowers(followersGroup);
                addNewEvent(following,follower, " stopped following you!");
                return new ResponseEntity<>("User stopped being followed",HttpStatus.OK);
            }
            return new ResponseEntity<>("Following relation doesn't exists",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> getFriendsList(Authentication authentication){
        try {
            UserEntity activeUser=userDAO.findUserByEmail(authentication.getName());
            List<Long> friendsId=followerDAO.getFriendsId(activeUser.getId());
            List<FriendsListResponse> friendsList =new ArrayList<>();
            if(friendsId.isEmpty()){
                return new ResponseEntity<>(friendsList,HttpStatus.OK);
            }
            for(Long id:friendsId) {
                if(followerDAO.checkIfFollowerExistsById(id, activeUser.getId())) {
                    UserEntity friend = userDAO.findUserById(id);
                    if(friendsList.size()<8){
                        if(friend.getActive()){
                            FriendsListResponse friends = new FriendsListResponse(friend,
                                    convertImagePathToImage(friend.getUserInfo().getProfilePhotoSource()));
                            friendsList.add(friends);
                        }
                    }
                }
            }
            return  new ResponseEntity<>(friendsList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getLastEvents(GetEventsRequest request) {
        try {
            EventDTOMapper mapper = new EventDTOMapper();
            String email = jwtUtil.getSubject(request.token());
            UserEntity eventReceiver = userDAO.findUserByEmail(email);
            List<Event> events = eventDAO.getLastEvents(eventReceiver, request.numberOfEvents());
            List<EventDTO> eventsDTO = new LinkedList<>();
            for(Event e: events) {
                eventsDTO.add(mapper.apply(e));
            }
            return new ResponseEntity<>(eventsDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void addNewEvent(UserEntity receiver, UserEntity creator, String message) {

        Event event = new Event(LocalDateTime.now(),message, receiver, creator);
        eventDAO.saveEvent(event);
    }

    private Blob convertImagePathToImage(String path) throws IOException, SQLException {
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        return new SerialBlob( new InputStreamResource(inputStream).getContentAsByteArray());
    }
}
