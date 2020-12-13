package com.cloudapps.springbooks.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cloudapps.springbooks.model.api.request.PostBookRequest;
import com.cloudapps.springbooks.model.api.request.PostCommentRequest;
import com.cloudapps.springbooks.model.api.request.PostUserRequest;
import com.cloudapps.springbooks.model.api.request.UpdateUserEmailRequest;
import com.cloudapps.springbooks.model.api.response.BookSummaryResponse;
import com.cloudapps.springbooks.model.api.response.DeleteCommentResponse;
import com.cloudapps.springbooks.model.api.response.GetBookResponse;
import com.cloudapps.springbooks.model.api.response.GetCommentsFromUserResponseElement;
import com.cloudapps.springbooks.model.api.response.PostCommentResponse;
import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface RestApi {
	
	@Operation(
			summary = "Find all books in the system", 
			description = "Find all books in the system", 
			tags = { "books" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookSummaryResponse.class)))),
        @ApiResponse(responseCode = "500", description = "internal server error") })
	@GetMapping(value="books")
	public ResponseEntity<List<BookSummaryResponse>> getBooks();

	@Operation(
			summary = "Get the information of a specific book and its comments", 
			description = "Get the information of a specific book and its comments", 
			tags = { "books" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = GetBookResponse.class)))),
        @ApiResponse(responseCode = "404", description = "book not found") })
	@GetMapping(value="books/{bookId}")
	public ResponseEntity<GetBookResponse> getBook(@PathVariable(value="bookId") Long bookId);
	
	@Operation(
			summary = "Add a new book", 
			description = "Add a new book", 
			tags = { "books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "book successfully created",
                content = @Content(schema = @Schema(implementation = Book.class))),
        @ApiResponse(responseCode = "500", description = "internal server error") })
	@PostMapping(value="books")
	public ResponseEntity<Book> postBook(@RequestBody PostBookRequest postBookRequest);
	
	@Operation(
			summary = "Add a new comment to a book", 
			description = "Add a new comment to a book", 
			tags = { "books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "comment successfully created",
                content = @Content(schema = @Schema(implementation = PostCommentResponse.class))),
        @ApiResponse(responseCode = "400", description = "bad request"),
        @ApiResponse(responseCode = "500", description = "internal server error") })
	@PostMapping(value="books/{bookId}/comments")
	public ResponseEntity<PostCommentResponse> postComment(@PathVariable(value="bookId") Long bookId,
			@RequestBody PostCommentRequest postCommentRequest);
	
	@Operation(
			summary = "Deletes a comment for a specific book", 
			description = "Deletes a comment for a specific book", 
			tags = { "books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "comment successfully deleted",
        		content = @Content(schema = @Schema(implementation = DeleteCommentResponse.class))),
        @ApiResponse(responseCode = "404", description = "comment not found") })
	@DeleteMapping(value="books/{bookId}/comments/{commentId}")
	public ResponseEntity<DeleteCommentResponse> deleteComment(
			@PathVariable(value="bookId") Long bookId, 
			@PathVariable(value="commentId") Long commentId);

	@Operation(
			summary = "Add a new user", 
			description = "Add a new user", 
			tags = { "users" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "user successfully created",
                content = @Content(schema = @Schema(implementation = PostUserRequest.class))),
        @ApiResponse(responseCode = "500", description = "internal server error") })
	@PostMapping(value="users")
	public ResponseEntity<User> postUser(@RequestBody PostUserRequest postUserRequest);
	
	@Operation(
			summary = "Get the information of a specific user", 
			description = "Get the information of a specific user", 
			tags = { "users" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        @ApiResponse(responseCode = "404", description = "user not found") })
	@GetMapping(value="users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable(value="userId") Long userId);
	
	@Operation(
			summary = "Update the email from a specific user", 
			description = "Update the email from a specific user", 
			tags = { "users" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        @ApiResponse(responseCode = "404", description = "user not found"),
        @ApiResponse(responseCode = "500", description = "internal server error") })
	@PatchMapping(path = "users/{userId}")
	public ResponseEntity<User> updateUserEmail(@PathVariable long userId, @RequestBody UpdateUserEmailRequest request);
	
	@Operation(
			summary = "Deletes a user", 
			description = "Deletes a user", 
			tags = { "users" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "user successfully deleted",
        		content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "user with comments"),
        @ApiResponse(responseCode = "404", description = "user not found") })
	@DeleteMapping(value="users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable(value="userId") Long userId);
	
	@Operation(
			summary = "Find all comments from a user", 
			description = "Find all comments from a user", 
			tags = { "users" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = GetCommentsFromUserResponseElement.class)))),
        @ApiResponse(responseCode = "500", description = "internal server error") })
	@GetMapping(value="users/{userId}/comments")
	public ResponseEntity<List<GetCommentsFromUserResponseElement>> getCommentsFromUser(
			@PathVariable(value="userId") Long userId);
	
}
