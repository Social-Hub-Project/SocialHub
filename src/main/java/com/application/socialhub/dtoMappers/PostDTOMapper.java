package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.PostDTO;
import com.application.socialhub.model.Post;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDTOMapper implements Function<Post, PostDTO> {

    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(post.getDescription(),
                post.getPhoto_source());
    }
}
