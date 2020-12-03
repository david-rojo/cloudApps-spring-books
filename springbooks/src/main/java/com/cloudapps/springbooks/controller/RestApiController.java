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

import com.cloudapps.springbooks.model.api.BookSummaryResponse;
import com.cloudapps.springbooks.model.api.GetBookResponse;
import com.cloudapps.springbooks.model.api.PostBookRequest;
import com.cloudapps.springbooks.model.api.PostCommentRequest;
import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;
import com.cloudapps.springbooks.service.BookService;
import com.cloudapps.springbooks.service.CommentService;

@RestController
@RequestMapping (value="springbooks")
public class RestApiController implements RestApi {

	private Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CommentService commentsService;
	
	@GetMapping(value="books")
	public ResponseEntity<List<BookSummaryResponse>> getBooks() {
		log.info("getBooks method invoked");
		List<BookSummaryResponse> responseList = new ArrayList<>();
		this.bookService.findAll()
			.forEach(book -> responseList.add(new BookSummaryResponse(book.getId(), book.getTitle())));
		return ResponseEntity.status(HttpStatus.OK).body(responseList);		
	}
	
	@GetMapping(value="books/{bookId}")
	public ResponseEntity<GetBookResponse> getBook(@PathVariable(value="bookId") Long bookId) {
		log.info("getBook method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId);
		GetBookResponse response = new GetBookResponse();
		Optional<Book> book = this.bookService.findById(bookId);
		if (book.isPresent()) {
			response.setBook(book.get());
			this.commentsService.findAllCommentsByBook(bookId).forEach(comment -> response.addComment(comment));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);		
	}
	
	@GetMapping(value="books/{bookId}/comments")
	public ResponseEntity<List<Comment>> getCommentsFromBook(@PathVariable(value="bookId") Long bookId) {
		log.info("getCommentsFromBook method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId);
		if (this.bookService.findById(bookId).isPresent()) {
			List<Comment> responseList = new ArrayList<>();
			this.commentsService.findAllCommentsByBook(bookId)
				.forEach(comment -> responseList.add(comment));
			return ResponseEntity.status(HttpStatus.OK).body(responseList);	
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());				
	}
	
	@PostMapping(value="books")
	public ResponseEntity<String> postBook(@RequestBody PostBookRequest postBookRequest) {
		log.info("postBook method invoked");
		log.info("parameters received => "
				+ "\"postBookRequest\" = " + postBookRequest.toString());
		bookService.save(new Book(
				postBookRequest.getTitle(),
				postBookRequest.getSummary(),
				postBookRequest.getAuthor(),
				postBookRequest.getPublisher(),
				postBookRequest.getPublicationYear()));
		return ResponseEntity.status(HttpStatus.CREATED).body("Book created");		
	}
	
	@PostMapping(value="books/{bookId}/comments")
	public ResponseEntity<String> postComment(@PathVariable(value="bookId") Long bookId,
			@RequestBody PostCommentRequest postCommentRequest) {
		log.info("postComment method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId + ", "
				+ "\"postCommentRequest\" = " + postCommentRequest.toString());
		if (this.commentsService.isScoreValid(postCommentRequest.getScore())) {
			commentsService.save(new Comment(
					postCommentRequest.getText(),
					postCommentRequest.getUsername(),
					postCommentRequest.getScore())
				, bookId);
			return ResponseEntity.status(HttpStatus.CREATED).body("Comment created for bookId " + bookId);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid score: " + postCommentRequest.getScore() + 
					". Valid scores: 0, 1, 2, 3, 4, 5");
		}
	}
	
	@DeleteMapping(value="books/{bookId}/comments/{commentId}")
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
