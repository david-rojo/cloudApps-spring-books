package com.cloudapps.springbooks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

		model.addAttribute("books", bookService.findAll());
		model.addAttribute("welcome", session.isNew());

		return "index";
	}
}
