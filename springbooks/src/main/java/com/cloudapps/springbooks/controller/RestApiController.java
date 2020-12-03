package com.cloudapps.springbooks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudapps.springbooks.model.api.BookSummary;
import com.cloudapps.springbooks.model.api.FullBook;
import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;
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
	
	@GetMapping(value="books/{bookId}")
	public ResponseEntity<FullBook> getBook(@PathVariable(value="bookId") Long bookId) {
		log.info("getBook method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId);
		FullBook fullBook = new FullBook();
		Optional<Book> book = this.bookService.findById(bookId);
		if (book.isPresent()) {
			fullBook.setBook(book.get());
			this.commentsService.findAllCommentsByBook(bookId).forEach(comment -> fullBook.addComment(comment));
			return ResponseEntity.status(HttpStatus.OK).body(fullBook);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);		
	}
	
	@GetMapping(value="books/{bookId}/comments")
	public ResponseEntity<List<Comment>> getCommentsFromBook(@PathVariable(value="bookId") Long bookId) {
		log.info("getCommentsFromBook method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId);
		List<Comment> comments = new ArrayList<>();
		this.commentsService.findAllCommentsByBook(bookId)
			.forEach(comment -> comments.add(comment));
		return ResponseEntity.status(HttpStatus.OK).body(comments);		
	}
	
	@PostMapping(value="books")
	public ResponseEntity<String> postBook(@RequestBody Book book) {
		log.info("postBook method invoked");
		log.info("parameters received => "
				+ "\"book\" = " + book.toString());
		bookService.save(book);
		return ResponseEntity.status(HttpStatus.OK).body("Book added");		
	}
	
	@PostMapping(value="books/{bookId}/comments")
	public ResponseEntity<String> postComment(@PathVariable(value="bookId") Long bookId, @RequestBody Comment comment) {
		log.info("postComment method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId + ", "
				+ "\"comment\" = " + comment.toString());
		commentsService.save(comment, bookId);
		return ResponseEntity.status(HttpStatus.OK).body("Comment added for bookId " + bookId);		
	}
	
	@DeleteMapping(value="books/{bookId}/comments/{commentId}" )
	public ResponseEntity<String> deleteComment(
			@PathVariable(value="bookId") Long bookId, 
			@PathVariable(value="commentId") Long commentId) {
		log.info("deleteComment method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId + ", "
				+ "\"commentId\" = " + commentId);
		Boolean deleted = commentsService.deleteById(bookId, commentId);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.OK).body("deleted");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
	}	
	
}
