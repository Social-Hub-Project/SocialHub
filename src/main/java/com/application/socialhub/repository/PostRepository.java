package com.application.socialhub.repository;

import com.application.socialhub.model.Post;
import com.application.socialhub.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    @Transactional
    @Query(value="SELECT * FROM post ORDER BY create_at DESC ", nativeQuery = true)
    List<Post> findAllPostsOrderedByCreatedAtDesc();
    @Query(value="SELECT * FROM post WHERE id = :postId AND user_id = :userId", nativeQuery = true)
    Post findPostByIdAndUserID (@Param("postId") long postId,@Param("userId") long userId);

    @Query(value="SELECT * FROM post WHERE user_entity_id=:userId ORDER BY create_at DESC", nativeQuery = true)
    List<Post> findAllPostsOrderedByCreatedAtDescForDedUser( @Param("userId") long userId);

    @Transactional
    @Modifying
    @Query(value="UPDATE post SET blocked= :state WHERE id = :postId", nativeQuery = true)
    void blockPost(@Param("state") boolean state, @Param("postId") long postId);

}
