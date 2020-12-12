package com.cloudapps.springbooks.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudapps.springbooks.model.api.request.PostBookRequest;
import com.cloudapps.springbooks.model.api.request.PostCommentRequest;
import com.cloudapps.springbooks.model.api.request.PostUserRequest;
import com.cloudapps.springbooks.model.api.request.UpdateUserEmailRequest;
import com.cloudapps.springbooks.model.api.response.BookSummaryResponse;
import com.cloudapps.springbooks.model.api.response.GetBookResponse;
import com.cloudapps.springbooks.model.api.response.GetBookResponseComment;
import com.cloudapps.springbooks.model.api.response.GetCommentsFromUserResponseElement;
import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;
import com.cloudapps.springbooks.model.entity.User;
import com.cloudapps.springbooks.service.BookService;
import com.cloudapps.springbooks.service.CommentService;
import com.cloudapps.springbooks.service.UserService;

@RestController
@RequestMapping (value="springbooks/api/v2")
public class RestApiController implements RestApi {

	private Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CommentService commentsService;
	
	@Autowired
	private UserService usersService;
	
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
		Optional<Book> book = this.bookService.findById(bookId);
		if (book.isPresent()) {
			GetBookResponse response = new GetBookResponse();
			response.setId(book.get().getId());
			response.setTitle(book.get().getTitle());
			response.setAuthor(book.get().getAuthor());
			response.setSummary(book.get().getSummary());
			response.setPublisher(book.get().getPublisher());
			response.setPublicationYear(book.get().getPublicationYear());
			book.get().getComments().forEach(comment -> response.addComment(
					new GetBookResponseComment(
							comment.getText(), 
							comment.getUser().getNick(), 
							comment.getUser().getEmail())));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);		
	}
	
	@PostMapping(value="books")
	public ResponseEntity<Book> postBook(@RequestBody PostBookRequest postBookRequest) {
		log.info("postBook method invoked");
		log.info("parameters received => "
				+ "\"postBookRequest\" = " + postBookRequest.toString());
		Book savedBook = bookService.save(new Book(
				postBookRequest.getTitle(),
				postBookRequest.getSummary(),
				postBookRequest.getAuthor(),
				postBookRequest.getPublisher(),
				postBookRequest.getPublicationYear()));
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedBook.getId()).toUri();
		return ResponseEntity.created(location).body(savedBook);		
	}
	
	@PostMapping(value="books/{bookId}/comments")
	public ResponseEntity<Comment> postComment(@PathVariable(value="bookId") Long bookId,
			@RequestBody PostCommentRequest postCommentRequest) {
		log.info("postComment method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId + ", "
				+ "\"postCommentRequest\" = " + postCommentRequest.toString());
		if (this.commentsService.isScoreValid(postCommentRequest.getScore())) {

			if (this.usersService.isUserPresent(postCommentRequest.getNick())) {
				Book book = this.bookService.findById(bookId).orElseThrow();

				Comment comment = new Comment(
						postCommentRequest.getText(),
						postCommentRequest.getScore());

				comment.setBook(book);

				this.commentsService.save(comment);			

				URI location = fromCurrentRequest().path("/{id}")
						.buildAndExpand(comment.getId()).toUri();
				return ResponseEntity.created(location).body(comment);	
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Comment.NON_EXISTENT_NICK_COMMENT);
			}

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Comment.INVALID_SCORE_COMMENT);
		}
	}
	
	@DeleteMapping(value="books/{bookId}/comments/{commentId}")
	public ResponseEntity<Comment> deleteComment(
			@PathVariable(value="bookId") Long bookId, 
			@PathVariable(value="commentId") Long commentId) {
		log.info("deleteComment method invoked");
		log.info("parameters received => "
				+ "\"bookId\" = " + bookId + ", "
				+ "\"commentId\" = " + commentId);
		
		Comment comment = this.commentsService.findById(commentId).orElseThrow();
		
		this.commentsService.delete(comment);
		
		return ResponseEntity.ok(comment);
	}

	@PostMapping(value="users")
	public ResponseEntity<User> postUser(PostUserRequest postUserRequest) {
		
		log.info("postUser method invoked");
		
		if (!this.usersService.isUserPresent(postUserRequest.getNick())) {
			
			User user = this.usersService.save(new User(
					postUserRequest.getNick(),
					postUserRequest.getEmail()));			

			URI location = fromCurrentRequest().path("/{id}")
					.buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(location).body(user);	
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(User.EXISTING_NICK);
		}
	}
	
	@GetMapping(value="users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable(value="userId") Long userId){
		
		log.info("getUser method invoked");
		
		Optional<User> user = this.usersService.findById(userId);
		if (user.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@PatchMapping(path = "users/{userId}")
	public ResponseEntity<User> updateUserEmail(@PathVariable long userId, @RequestBody UpdateUserEmailRequest request) {

		log.info("updateUserEmail method invoked");
		
		Optional<User> user = this.usersService.findById(userId);
		if (user.isPresent()) {
			user.get().setEmail(request.getEmail());
			User updatedUser = this.usersService.save(user.get());
			return ResponseEntity.ok(updatedUser); 
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping(value="users/{userId}")
	public ResponseEntity<User> deleteUser(
			@PathVariable(value="userId") Long userId) {

		log.info("deleteUser method invoked");
		log.info("parameters received => "
				+ "\"userId\" = " + userId);
		
		User user = this.usersService.findById(userId).orElseThrow();
		
		if (this.commentsService.findByNick(user.getNick()).isEmpty()) {
			this.usersService.delete(user);
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(User.USER_WITH_COMMENTS);
		}		
	}
	
	@GetMapping(value="users/{userId}/comments")
	public ResponseEntity<List<GetCommentsFromUserResponseElement>> getCommentsFromUser(
			@PathVariable(value="userId") Long userId) {

		log.info("getCommentsFromUser method invoked");
		log.info("parameters received => "
				+ "\"userId\" = " + userId);
		
		User user = this.usersService.findById(userId).orElseThrow();
		
		List<GetCommentsFromUserResponseElement> responseList = new ArrayList<>();
		this.commentsService.findByNick(user.getNick()).forEach(comment -> 
			responseList.add(new GetCommentsFromUserResponseElement(
					comment.getId(),
					comment.getText(),
					comment.getScore(),
					comment.getBook().getId())));
		return ResponseEntity.ok(responseList);		
	}	
	
}
