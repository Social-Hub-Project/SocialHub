package com.application.socialhub.repository;

import com.application.socialhub.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    @Transactional
    @Modifying
    @Query( value = "INSERT INTO Post(id,description, blocked, create_at, photo_source, id_user) VALUES " +
            "(:id,:description,:blocked, :createAt, :photoSource, :userId)", nativeQuery = true)
    void save_data(@Param("id") long id ,@Param("description") String description, @Param("blocked") boolean blocked,
           @Param("createAt") LocalDate createAt, @Param("photoSource") String photoSource, @Param("userId") Long userId);
    @Query(value = "SELECT MAX(id) FROM post", nativeQuery = true)
    Long findMaxId();

}
