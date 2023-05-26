package com.application.socialhub.dao;

import com.application.socialhub.model.Followers;

import java.util.List;

public interface FollowerDAO {

    void addFollower(Followers followers);

    boolean checkIfFollowerExists(long idFollower, long idFollowing);

    Followers findFollowers(long idFollower,long idFollowing);

    void deleteFollowers(Followers followers);

    List<Long> getFriendsId(long idUser);

    boolean checkIfFollowerExistsById(long idFollower, long idFollowing);
}
