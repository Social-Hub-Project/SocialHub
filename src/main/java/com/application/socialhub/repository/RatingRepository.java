package com.application.socialhub.repository;

import com.application.socialhub.model.Rating;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
    @Transactional
    @Modifying
    @Query( value = "INSERT INTO rating(id,assessment, modified_at, created_at,id_user,id_post) VALUES " +
            "(:id,:assessment,:modified_at, :created_at,:userId,:postId)", nativeQuery = true)
    void save_data(@Param("id") long id , @Param("assessment") boolean assessment, @Param("modified_at") LocalDate modified_at,
                   @Param("created_at") LocalDate created_at,@Param("userId") Long userId,
                   @Param("postId") Long postId);

    @Query(value = "SELECT MAX(id) FROM rating", nativeQuery = true)
    Long findMaxId();

    @Query(value = "SELECT MAX(id) FROM post", nativeQuery = true)
    Long findMaxIdPost();
}
