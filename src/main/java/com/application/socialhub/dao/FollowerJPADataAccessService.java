package com.application.socialhub.dao;

import com.application.socialhub.model.Followers;
import com.application.socialhub.repository.FollowerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Followers findFollowers(long idFollower, long idFollowing) {
        return followerRepository.findFollowers(idFollower,idFollowing);
    }

    @Override
    public void deleteFollowers(Followers followers) {
        followerRepository.delete(followers);
    }

    @Override
    public List<Long> getFriendsId(long idUser) {
        return followerRepository.getFriendsId(idUser);
    }

    @Override
    public boolean checkIfFollowerExistsById(long idFollower, long idFollowing) {
        return followerRepository.existsByFollowerIdAndFollowingId(idFollower,idFollowing);
    }
}
