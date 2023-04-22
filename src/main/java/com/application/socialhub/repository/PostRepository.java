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
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
