package com.application.socialhub.dao;

import com.application.socialhub.repository.PostCategoryRepository;
import org.springframework.stereotype.Repository;

@Repository("postCategoryConnection")
public class PostCategoryJPADataAccessService implements PostCategoryDAO {

    private final PostCategoryRepository repository;

    public PostCategoryJPADataAccessService(PostCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCategoryConnection(long postId) {
        repository.removeCategoryConnection(postId);
    }
}
