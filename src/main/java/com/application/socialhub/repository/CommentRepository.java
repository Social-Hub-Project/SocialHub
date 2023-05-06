package com.application.socialhub.repository;

import com.application.socialhub.model.Comment;
import com.application.socialhub.model.PostsReturns;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    @Query(value = "SELECT new com.application.socialhub.model.PostsReturns(c.content, c.created_at, c.userEntity) " +
            "FROM Comment c WHERE c.posts.id =:postId")
    List<PostsReturns> findPostById(@Param("postId") Long postId);
}
