package com.cloudapps.springbooks.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudapps.springbooks.model.api.BookSummary;
import com.cloudapps.springbooks.service.BookService;
import com.cloudapps.springbooks.service.CommentService;

@RestController
@RequestMapping (value="springbooks")
public class RestApiController {

	private Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CommentService commentsService;
	
	@GetMapping(value="books")
	public ResponseEntity<List<BookSummary>> getBooks() {
		log.info("getBooks method invoked");
		List<BookSummary> bookSummaries = new ArrayList<>();
		this.bookService.findAll()
			.forEach(book -> bookSummaries.add(new BookSummary(book.getId(), book.getTitle())));
		return ResponseEntity.status(HttpStatus.OK).body(bookSummaries);		
	}
}
