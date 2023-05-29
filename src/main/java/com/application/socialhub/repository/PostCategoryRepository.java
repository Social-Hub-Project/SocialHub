package com.application.socialhub.repository;


import com.application.socialhub.model.PostCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long>{

    @Modifying
    @Transactional
    @Query(value = "delete from post_categories where post_id=:postId",nativeQuery = true)
    void removeCategoryConnection(@Param("postId") long postId);
}
