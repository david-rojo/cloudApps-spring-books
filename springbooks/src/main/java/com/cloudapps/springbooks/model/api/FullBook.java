package com.cloudapps.springbooks.model.api;

import java.util.ArrayList;
import java.util.List;

import com.cloudapps.springbooks.model.entity.Book;
import com.cloudapps.springbooks.model.entity.Comment;

public class FullBook {

	private Book book;
	
	private List<Comment> comments;
	
	public FullBook() {
		comments = new ArrayList<>();
	}
	
	public FullBook(Book book, List<Comment> comments) {
		this.book = book;
		this.comments = comments;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void getComment(int index) {
		this.comments.get(index);
	}
	
	public void removeComment(int index) {
		this.comments.remove(index);
	}
	
}
