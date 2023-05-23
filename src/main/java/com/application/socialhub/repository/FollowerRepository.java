package com.application.socialhub.repository;


import com.application.socialhub.model.Followers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FollowerRepository  extends JpaRepository<Followers, Long>{

    @Query(value = "SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Followers f WHERE f.follower.id = :idFollower AND f.following.id = :idFollowing")
    boolean  checkIfFollowerExists(long idFollower, long idFollowing);

}
