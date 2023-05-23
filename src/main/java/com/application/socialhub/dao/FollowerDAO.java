package com.application.socialhub.dao;

import com.application.socialhub.model.Followers;

public interface FollowerDAO {

    void addFollower(Followers followers);

    boolean checkIfFollowerExists(long idFollower, long idFollowing);
}
