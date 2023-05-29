package com.application.socialhub.repository;

import com.application.socialhub.model.Rating;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
    @Transactional
    @Query(value = "SELECT * FROM Rating  WHERE user_entity_id = :userId AND posts_id = :postId",nativeQuery =
            true)
    Rating findRatingByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

    @Transactional
    @Query(value="SELECT COUNT(*) FROM rating WHERE posts_id = :postId AND assessment = 1", nativeQuery = true)
    int findPostLikes(@Param("postId") Long postId);
    @Transactional
    @Query(value="SELECT COUNT(*) FROM rating WHERE posts_id = :postId AND assessment = -1", nativeQuery = true)
    int findPostDislikes(@Param("postId") Long postId);


    @Query(value = "SELECT assessment FROM rating WHERE user_entity_id = :userID AND posts_id = :postId",
            nativeQuery = true)
    Integer ratingUser(@Param("userID") long userID, @Param("postId") long postID);

    @Modifying
    @Transactional
    @Query(value = "delete from rating where posts_id=:postId",nativeQuery = true)
    void deleteRatingByPostId(@Param("postId") long postId);

}
