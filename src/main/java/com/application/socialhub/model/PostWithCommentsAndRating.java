package com.application.socialhub.model;

import java.util.List;
import java.sql.Blob;

public class PostWithCommentsAndRating {

    private Post post;
    private List<PostsReturns> comments;
    private int like;
    private int dislike;

    private Integer lickedByUser;
    private Blob image;
    private List<Blob> profileImage;

    public PostWithCommentsAndRating(Post post, List<PostsReturns> comments, int like, int dislike, Integer lickedByUser,
                                     Blob image, List<Blob> profileImage) {
        this.post = post;
        this.comments = comments;
        this.like = like;
        this.dislike = dislike;
        this.image = image;
        this.profileImage = profileImage;
        this.lickedByUser = lickedByUser;
    }

    public Integer getLickedByUser() {
        return lickedByUser;
    }

    public void setLickedByUser(Integer lickedByUser) {
        this.lickedByUser = lickedByUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<PostsReturns> getComments() {
        return comments;
    }

    public void setComments(List<PostsReturns> comments) {
        this.comments = comments;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public List<Blob> getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(List<Blob> profileImage) {
        this.profileImage = profileImage;
    }
}
