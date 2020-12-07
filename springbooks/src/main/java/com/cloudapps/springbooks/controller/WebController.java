package com.cloudapps.springbooks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudapps.springbooks.model.api.BookSummaryResponse;
import com.cloudapps.springbooks.model.api.GetBookResponse;
import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;
import com.cloudapps.springbooks.service.BookService;
import com.cloudapps.springbooks.service.CommentService;
import com.cloudapps.springbooks.service.UserSession;

@Controller
public class WebController {

	private Logger log = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CommentService commentsService;
	
	@GetMapping("/")
	public String showBooks(Model model, HttpSession session) {
		
		log.info("showBooks method invoked");

		List<BookSummaryResponse> books = new ArrayList<>();
		this.bookService.findAll()
			.forEach(book -> books.add(new BookSummaryResponse(book.getId(), book.getTitle())));
		model.addAttribute("books", books);
		
		model.addAttribute("welcome", session.isNew());

		return "index";
	}
	
	@GetMapping("/book/{bookId}")
	public String showBook(Model model, @PathVariable int bookId) {
		
		log.info("showBook method invoked");
		
		GetBookResponse bookInfo = new GetBookResponse();
		Optional<Book> book = this.bookService.findById(bookId);
		bookInfo.setBook(book.get());
		this.commentsService.findAllCommentsByBook(Long.valueOf(bookId))
			.forEach(comment -> bookInfo.addComment(comment));
		model.addAttribute("bookInfo", bookInfo);
		
		model.addAttribute("user", userSession.getUser());

		return "show_book";
	}
	
	@PostMapping("/book/new")
	public String newBook(Model model, @RequestParam String title, @RequestParam String author,
			@RequestParam String summary, @RequestParam String publisher,  @RequestParam String publicationYear) {
		
		log.info("newBook method invoked");
		
		Book savedBook = bookService.save(new Book(title,summary,author, publisher, Integer.parseInt(publicationYear)));
		model.addAttribute("savedBook", savedBook);
		
		return "saved_book";
	}
	
	@GetMapping("book/{bookId}/comment/{commentId}/delete")
	public String deletePost(Model model, @PathVariable int bookId, @PathVariable int commentId) {

		log.info("deletePost method invoked");
		
		this.commentsService.deleteById(Long.valueOf(bookId), Long.valueOf(commentId));
		model.addAttribute("bookId", bookId);

		return "deleted_comment";
	}
	
	@PostMapping("/book/{bookId}/comment/new")
	public String newComment(Model model, @PathVariable int bookId, @RequestParam String user, @RequestParam String score,
			@RequestParam String text) {
		
		log.info("newComment method invoked");
		Comment savedComment = this.commentsService.save(new Comment(text, user, Integer.parseInt(score)), Long.valueOf(bookId));
		model.addAttribute("savedComment", savedComment);
		String bookTitle = this.bookService.findById(Long.valueOf(bookId)).get().getTitle();
		model.addAttribute("bookTitle", bookTitle);
		model.addAttribute("bookId", bookId);
		
		userSession.setUser(user);
		
		return "saved_comment";
	}
	
	
}
