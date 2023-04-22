package com.application.socialhub.dao;

import com.application.socialhub.model.Rating;
import com.application.socialhub.repository.RatingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("ratings")
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

    @Override
    public void deleteRating(Rating rating) {
        repository.delete(rating);
    }

    @Override
    public Rating checkRating(long userId, long postId) {
        return repository.findRatingByUserIdAndPostId(userId, postId);
    }


}
