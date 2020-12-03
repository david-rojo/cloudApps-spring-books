package com.cloudapps.springbooks.model.entity;

public class Comment {
	
	private Long id;
	
	private String text;
	
	private String username;
	
	private int score;
	
	public Comment() {
		
	}
	
	public Comment(String text, String username, int score) {
		super();
		this.text = text;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", "
				+ "text=" + text + ", "
				+ "username" + username + ", "
				+ "score=" + score + "]";
	}
}
