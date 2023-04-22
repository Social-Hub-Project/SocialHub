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

//    @Query(value="SELECT p, COUNT(l.id) as likeCount,COUNT(l.id) as dislikeCount"+
//            "FROM post p"+
//            "left join l"
//
//
//            ,nativeQuery = true)
//    List<Object[]> findAllPostsOrderByCreationDateDescWithLikesAndDislikes();
    
}
