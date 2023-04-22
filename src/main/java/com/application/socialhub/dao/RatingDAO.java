package com.application.socialhub.dao;

import com.application.socialhub.model.Rating;

import java.util.List;

public interface RatingDAO {
    List<Rating> findAllRatings();

    void saveRating(Rating newRating);
}
