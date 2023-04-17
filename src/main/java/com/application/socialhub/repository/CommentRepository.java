package com.application.socialhub.repository;

import com.application.socialhub.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    @Query(value = "SELECT MAX(id) FROM comment", nativeQuery = true)
    Long findMaxId();

    @Transactional
    @Modifying
    @Query( value = "INSERT INTO comment(id,content,created_at,id_user,post_id) VALUES " +
            "(:id,:content,:createAt,:userId,:postId)", nativeQuery = true)
    void save_data(@Param("id") long id , @Param("content") String content,
                   @Param("createAt") LocalDate createAt, @Param("userId") Long userId, @Param("postId") Long postId);

    @Query(value="SELECT MAX (id) FROM post", nativeQuery = true)
    long findMaxIdPost();
}
