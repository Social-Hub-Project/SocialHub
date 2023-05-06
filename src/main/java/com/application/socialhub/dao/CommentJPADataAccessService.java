package com.application.socialhub.dao;


import com.application.socialhub.model.Comment;
import com.application.socialhub.model.PostsReturns;
import com.application.socialhub.repository.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("comment")
public class CommentJPADataAccessService implements CommentDAO{

    private final CommentRepository repository;

    public CommentJPADataAccessService(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> findAllComments() {
        return repository.findAll();
    }

    @Override
    public void saveComment(Comment newComment) {
        repository.save(newComment);
    }

    @Override
    public List<PostsReturns> findCommentsByPostId(long postId) {
        return repository.findPostById(postId);
    }

}
