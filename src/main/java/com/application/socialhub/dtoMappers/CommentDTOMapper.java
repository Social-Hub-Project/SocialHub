package com.application.socialhub.dtoMappers;

import com.application.socialhub.dto.CommentDTO;
import com.application.socialhub.model.Comment;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CommentDTOMapper implements Function<Comment, CommentDTO> {

    @Override
    public CommentDTO apply(Comment comment) {
        return new CommentDTO(comment.getContent(), comment.getPost().getId());
    }
}
