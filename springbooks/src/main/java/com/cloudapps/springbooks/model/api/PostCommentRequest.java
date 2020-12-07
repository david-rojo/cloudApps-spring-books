package com.cloudapps.springbooks.model.api;

public class PostCommentRequest {
		
	private String text;
	
	private String user;
	
	private int score;
	
	public PostCommentRequest() {
		
	}
	
	public PostCommentRequest(String text, String user, int score) {
		super();
		this.text = text;
		this.user = user;
		this.score = score;
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

	@Override
	public String toString() {
		return "PostCommentRequest [text=" + text + ", "
				+ "user" + user + ", "
				+ "score=" + score + "]";
	}
}
