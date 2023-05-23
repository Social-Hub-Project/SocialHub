package com.application.socialhub.dao;

import com.application.socialhub.model.Followers;
import com.application.socialhub.repository.FollowerRepository;
import org.springframework.stereotype.Repository;

@Repository("follower")
public class FollowerJPADataAccessService implements FollowerDAO{

    private final FollowerRepository followerRepository;

    public FollowerJPADataAccessService(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }


    @Override
    public void addFollower(Followers followers) {
        followerRepository.save(followers);
    }

    @Override
    public boolean checkIfFollowerExists(long idFollower, long idFollowing) {
        return followerRepository.checkIfFollowerExists(idFollower, idFollowing);
    }
}
