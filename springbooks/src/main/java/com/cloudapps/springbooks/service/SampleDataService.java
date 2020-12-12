package com.cloudapps.springbooks.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;
import com.cloudapps.springbooks.model.entity.User;

@Service
public class SampleDataService {
	
	@Autowired
	private BookService books;
	
	@Autowired
	private UserService users;	
	
	@PostConstruct
	public void init() {
		User user = new User("david", "david@urjc.es");
		users.save(user);
		
		Comment comment1 = new Comment("Very interesting", 4);
		comment1.setUser(user);
		Book book1 = new Book(
				"Don Quixote", 
				"Summary of Don Quixote",
				"Miguel de Cervantes",
				"CloudApps Classics",
				1512);
		book1.addComment(comment1);
		books.save(book1);
		
		Comment comment2 = new Comment("I love it", 5);
		comment2.setUser(user);
		Book book2 = new Book(
				"The Little Prince",
				"Summary of The Little Prince",
				"Antoine de Saint-Exupéry",
				"CloudApps Classics",
				1943);
		book2.addComment(comment2);
		books.save(book2);
	}
}
