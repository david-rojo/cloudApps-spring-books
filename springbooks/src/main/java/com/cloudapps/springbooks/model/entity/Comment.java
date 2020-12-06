package com.cloudapps.springbooks.model.entity;

public class Comment {
	
	public static Comment INVALID_SCORE_COMMENT = new Comment(
			"Invalid score. Valid scores: 0, 1, 2, 3, 4, 5", "", 0);
	
	private Long id;
	
	private String text;
	
	private String user;
	
	private int score;
	
	public Comment() {
		
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", "
				+ "text=" + text + ", "
				+ "user" + user + ", "
				+ "score=" + score + "]";
	}
}
