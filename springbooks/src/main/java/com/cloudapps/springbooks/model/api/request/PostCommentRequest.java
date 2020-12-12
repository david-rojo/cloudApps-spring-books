package com.cloudapps.springbooks.model.api.request;

public class PostCommentRequest {
		
	private String text;
	
	private String nick;
	
	private int score;
	
	public PostCommentRequest() {
		
	}
	
	public PostCommentRequest(String text, String nick, int score) {
		super();
		this.text = text;
		this.nick = nick;
		this.score = score;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "PostCommentRequest [text=" + text + ", "
				+ "nick" + nick + ", "
				+ "score=" + score + "]";
	}
}
