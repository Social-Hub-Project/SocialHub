package com.application.socialhub.dao;

import com.application.socialhub.model.Comment;
import com.application.socialhub.model.PostsReturns;

import java.util.List;


public interface CommentDAO {
    List<Comment> findAllComments();

    void saveComment(Comment newComment);

    List<PostsReturns> findCommentsByPostId(long postId);
}
