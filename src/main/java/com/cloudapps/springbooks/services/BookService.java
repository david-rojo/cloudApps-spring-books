package com.cloudapps.springbooks.services;

import java.util.Collection;

import com.cloudapps.springbooks.dtos.requests.BookRequestDto;
import com.cloudapps.springbooks.dtos.responses.BookDetailsResponseDto;
import com.cloudapps.springbooks.dtos.responses.BookResponseDto;

public interface BookService {

    Collection<BookResponseDto> findAll();

    BookDetailsResponseDto save(BookRequestDto bookRequestDto);

    BookDetailsResponseDto findById(long bookId);

}
