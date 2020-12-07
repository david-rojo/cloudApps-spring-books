package com.cloudapps.springbooks.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cloudapps.springbooks.model.api.BookSummaryResponse;
import com.cloudapps.springbooks.model.api.GetBookResponse;
import com.cloudapps.springbooks.model.api.PostBookRequest;
import com.cloudapps.springbooks.model.api.PostCommentRequest;
import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "books")
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
                content = @Content(schema = @Schema(implementation = Comment.class))),
        @ApiResponse(responseCode = "400", description = "bad request"),
        @ApiResponse(responseCode = "500", description = "internal server error") })
	@PostMapping(value="books/{bookId}/comments")
	public ResponseEntity<Comment> postComment(@PathVariable(value="bookId") Long bookId,
			@RequestBody PostCommentRequest postCommentRequest);
	
	@Operation(
			summary = "Deletes a comment for a specific book", 
			description = "Deletes a comment for a specific book", 
			tags = { "books" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "comment successfully deleted",
        		content = @Content(schema = @Schema(implementation = Comment.class))),
        @ApiResponse(responseCode = "404", description = "comment not found") })
	@DeleteMapping(value="books/{bookId}/comments/{commentId}")
	public ResponseEntity<Comment> deleteComment(
			@PathVariable(value="bookId") Long bookId, 
			@PathVariable(value="commentId") Long commentId);
	
	@Operation(
			summary = "Get comments of a specific book", 
			description = "Get comments of a specific book", 
			tags = { "books" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Comment.class)))),
        @ApiResponse(responseCode = "404", description = "book not found") })
	@GetMapping(value="books/{bookId}/comments")
	public ResponseEntity<List<Comment>> getCommentsFromBook(@PathVariable(value="bookId") Long bookId);

}
