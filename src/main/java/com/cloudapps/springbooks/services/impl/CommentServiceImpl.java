package com.cloudapps.springbooks.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.dtos.requests.CommentRequestDto;
import com.cloudapps.springbooks.dtos.responses.CommentResponseDto;
import com.cloudapps.springbooks.dtos.responses.UserCommentResponseDto;
import com.cloudapps.springbooks.exceptions.BookNotFoundException;
import com.cloudapps.springbooks.exceptions.CommentNotFoundException;
import com.cloudapps.springbooks.exceptions.UserNotFoundException;
import com.cloudapps.springbooks.models.Book;
import com.cloudapps.springbooks.models.Comment;
import com.cloudapps.springbooks.models.User;
import com.cloudapps.springbooks.repositories.BookRepository;
import com.cloudapps.springbooks.repositories.CommentRepository;
import com.cloudapps.springbooks.repositories.UserRepository;
import com.cloudapps.springbooks.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private Mapper mapper;
    private CommentRepository commentRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(Mapper mapper, CommentRepository commentRepository, BookRepository bookRepository,
                              UserRepository userRepository) {
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public CommentResponseDto addComment(long bookId, CommentRequestDto commentRequestDto) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        User user = this.userRepository.findByNick(commentRequestDto.getUserNick()).orElseThrow(UserNotFoundException::new);
        Comment comment = this.mapper.map(commentRequestDto, Comment.class);
        comment.setBook(book);
        comment.setUser(user);
        comment = this.commentRepository.save(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public CommentResponseDto deleteComment(long bookId, long commentId) {
        Comment comment = this.commentRepository.findByBookIdAndId(bookId, commentId)
                .orElseThrow(CommentNotFoundException::new);
        this.commentRepository.delete(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public Collection<UserCommentResponseDto> getComments(long userId) {
        return this.commentRepository.findByUserId(userId).stream()
                .map(comment -> this.mapper.map(comment, UserCommentResponseDto.class))
                .collect(Collectors.toList());
    }

}
