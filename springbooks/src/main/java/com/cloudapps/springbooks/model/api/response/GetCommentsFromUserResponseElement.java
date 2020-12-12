package com.cloudapps.springbooks.model.api.response;

public class GetCommentsFromUserResponseElement {

	private Long id;
	
	private String text;
	
	private int score;
	
	private Long bookId;
	
	public GetCommentsFromUserResponseElement() {
		
	}

	public GetCommentsFromUserResponseElement(Long id, String text, int score, Long bookId) {
		super();
		this.id = id;
		this.text = text;
		this.score = score;
		this.bookId = bookId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
}
