package com.cloudapps.springbooks.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.dtos.requests.BookRequestDto;
import com.cloudapps.springbooks.dtos.responses.BookDetailsResponseDto;
import com.cloudapps.springbooks.dtos.responses.BookResponseDto;
import com.cloudapps.springbooks.exceptions.BookNotFoundException;
import com.cloudapps.springbooks.models.Book;
import com.cloudapps.springbooks.repositories.BookRepository;
import com.cloudapps.springbooks.services.BookService;
import com.cloudapps.springbooks.services.CommentService;

@Service
public class BookServiceImpl implements BookService {

    private Mapper mapper;
    private BookRepository bookRepository;
    private CommentService commentService;

    public BookServiceImpl(Mapper mapper, BookRepository bookRepository, CommentService commentService) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.commentService = commentService;
    }

    public Collection<BookResponseDto> findAll() {
        return this.bookRepository.findAll().stream()
                .map(book -> this.mapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    public BookDetailsResponseDto save(BookRequestDto bookRequestDto) {
        Book book = this.mapper.map(bookRequestDto, Book.class);
        book = this.bookRepository.save(book);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

    public BookDetailsResponseDto findById(long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }
    
    public Collection<BookDetailsResponseDto> findAllWithDetails() {
        return this.bookRepository.findAll().stream()
                .map(book -> this.mapper.map(book, BookDetailsResponseDto.class))
                .collect(Collectors.toList());
    }

	@Override
	public BookDetailsResponseDto delete(long bookId) {
		Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
		//Delete all the comments
		book.getComments().forEach(comment -> this.commentService.deleteComment(bookId, comment.getId()));
		bookRepository.delete(book);
		return this.mapper.map(book, BookDetailsResponseDto.class);
	}

}
