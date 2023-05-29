package com.application.socialhub.dao;

import com.application.socialhub.model.Post;

import java.util.List;

public interface PostDAO {

    List<Post> findAllPosts();

    void savePost(Post newPost);

    Post findPostById(Long id);

    List<Post> findPostByCreatedAt();

    void deletePost(Post post);

    void blockPost(boolean state, long postId);

    List<Post> findAllPostsOrderedByCreatedAtDescForDedUser(long userId);

}
