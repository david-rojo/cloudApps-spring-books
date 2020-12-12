package com.cloudapps.springbooks.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	
	public static Comment INVALID_SCORE_COMMENT = new Comment(
			"Invalid score. Valid scores: 0, 1, 2, 3, 4, 5", "", 0);
	
	public static Comment NON_EXISTENT_BOOKID_COMMENT = new Comment(
			"Any book in the system has as id the requested bookId, the comment must be associated to an existing book. "
			+ "Please verify it", "", 0);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String text;
	
	private String user;
	
	private int score;
	
	@ManyToOne
	@JsonIgnore
	private Book book;
	
	protected Comment() {
		
	}
	
	public Comment(String text, String user, int score) {
		super();
		this.text = text;
		this.user = user;
		this.score = score;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", "
				+ "text=" + text + ", "
				+ "user" + user + ", "
				+ "score=" + score + "]";
	}
}
