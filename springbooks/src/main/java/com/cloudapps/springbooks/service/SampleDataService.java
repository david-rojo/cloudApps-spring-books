package com.cloudapps.springbooks.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;

@Service
public class SampleDataService {
	
	@Autowired
	private BookService books;
	
	@PostConstruct
	public void init() {
		Book book1 = new Book(
				"Don Quixote", 
				"Summary of Don Quixote",
				"Miguel de Cervantes",
				"CloudApps Classics",
				1512);
		book1.addComment(new Comment("Very interesting", "enric", 4));
		books.save(book1);
		
		Book book2 = new Book(
				"The Little Prince",
				"Summary of The Little Prince",
				"Antoine de Saint-Exupéry",
				"CloudApps Classics",
				1943);
		book2.addComment(new Comment("I love it", "malena", 5));
		books.save(book2);
	}
}
