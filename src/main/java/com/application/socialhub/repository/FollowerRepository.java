package com.application.socialhub.repository;


import com.application.socialhub.model.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository  extends JpaRepository<Followers, Long>{

    @Query(value = "SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Followers f WHERE f.follower.id = :idFollower AND f.following.id = :idFollowing")
    boolean  checkIfFollowerExists(long idFollower, long idFollowing);

    @Query(value = "SELECT * FROM Followers f WHERE f.follower_id = :idFollower AND f.following_id = :idFollowing",nativeQuery = true)
    Followers findFollowers(@Param("idFollower") long idFollower,@Param("idFollowing") long idFollowing);

    @Query(value = "SELECT f.following_id FROM Followers f WHERE f.follower_id = :idUser",nativeQuery = true)
    List<Long> getFriendsId(long idUser);

    @Query(value = "SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Followers f WHERE f.follower.id = :idFollower AND f.following.id = :idFollowing")
    boolean existsByFollowerIdAndFollowingId(long idFollower, long idFollowing);
}
