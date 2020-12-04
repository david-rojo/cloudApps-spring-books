package com.cloudapps.springbooks.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudapps.springbooks.model.api.BookSummaryResponse;
import com.cloudapps.springbooks.service.BookService;
import com.cloudapps.springbooks.service.CommentService;

@Controller
public class WebController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CommentService commentsService;
	
	@GetMapping("/")
	public String showBooks(Model model, HttpSession session) {

		List<BookSummaryResponse> books = new ArrayList<>();
		this.bookService.findAll()
			.forEach(book -> books.add(new BookSummaryResponse(book.getId(), book.getTitle())));
		model.addAttribute("books", books);

		return "index";
	}
}
