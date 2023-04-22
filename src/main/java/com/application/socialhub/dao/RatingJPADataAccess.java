package com.application.socialhub.dao;

import com.application.socialhub.model.Rating;
import com.application.socialhub.repository.RatingRepository;

import java.util.List;

public class RatingJPADataAccess implements RatingDAO{

    private final RatingRepository repository;

    public RatingJPADataAccess(RatingRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Rating> findAllRatings() {
        return repository.findAll();
    }

    @Override
    public void saveRating(Rating newRating) {
        repository.save(newRating);
    }
}
