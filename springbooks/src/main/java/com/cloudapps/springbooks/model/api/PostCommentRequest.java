package com.cloudapps.springbooks.model.api;

public class PostCommentRequest {
		
	private String text;
	
	private String username;
	
	private int score;
	
	public PostCommentRequest() {
		
	}
	
	public PostCommentRequest(String text, String username, int score) {
		super();
		this.text = text;
		this.username = username;
		this.score = score;
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
		return "PostCommentRequest [text=" + text + ", "
				+ "username" + username + ", "
				+ "score=" + score + "]";
	}
}
