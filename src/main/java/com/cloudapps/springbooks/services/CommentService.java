package com.cloudapps.springbooks.services;

import java.util.Collection;

import com.cloudapps.springbooks.dtos.requests.CommentRequestDto;
import com.cloudapps.springbooks.dtos.responses.CommentResponseDto;
import com.cloudapps.springbooks.dtos.responses.UserCommentResponseDto;

public interface CommentService {

    CommentResponseDto addComment(long bookId, CommentRequestDto commentRequestDto);

    CommentResponseDto deleteComment(long bookId, long commentId);

    Collection<UserCommentResponseDto> getComments(long userId);

}
