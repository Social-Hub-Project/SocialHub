package com.application.socialhub.dao;

import com.application.socialhub.model.Post;
import com.application.socialhub.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("post")
public class PostJPADataAccessService implements PostDAO{
    private final PostRepository repository;

    public PostJPADataAccessService(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> findAllPosts() {
        return repository.findAll();
    }
}
