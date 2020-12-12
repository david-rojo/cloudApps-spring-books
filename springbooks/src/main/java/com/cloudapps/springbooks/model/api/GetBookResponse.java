package com.cloudapps.springbooks.model.api;

import java.util.ArrayList;
import java.util.List;

import com.cloudapps.springbooks.model.entity.Book;

public class GetBookResponse {

	private Book book;
	
	private List<GetBookResponseComment> comments;
	
	public GetBookResponse() {
		comments = new ArrayList<>();
	}
	
	public GetBookResponse(Book book, List<GetBookResponseComment> comments) {
		this.book = book;
		this.comments = comments;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<GetBookResponseComment> getComments() {
		return comments;
	}

	public void setComments(List<GetBookResponseComment> comments) {
		this.comments = comments;
	}
	
	public void addComment(GetBookResponseComment comment) {
		this.comments.add(comment);
	}
	
	public void getComment(int index) {
		this.comments.get(index);
	}
	
	public void removeComment(int index) {
		this.comments.remove(index);
	}
	
}
