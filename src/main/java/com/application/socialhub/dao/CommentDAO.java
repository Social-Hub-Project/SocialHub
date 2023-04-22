package com.application.socialhub.dao;

import com.application.socialhub.model.Comment;

import java.util.List;


public interface CommentDAO {
    List<Comment> findAllComments();

    void saveComment(Comment newComment);
}
