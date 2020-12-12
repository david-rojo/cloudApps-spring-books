package com.cloudapps.springbooks.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository books;
		
	@Autowired
	public BookService() {
		
	}
	
	public Collection<Book> findAll() {
		return books.findAll();
	}
	
	public Optional<Book> findById(long id) {
		return books.findById(id);
	}
	
	public Book save(Book book) {
		return this.books.save(book);
	}
	
	public void deleteById(long id) {
		books.deleteById(id);
	}	
	
	public boolean isBookPresent(Long id) {
		return this.books.findById(id).isPresent() ? true : false; 
	}
}
