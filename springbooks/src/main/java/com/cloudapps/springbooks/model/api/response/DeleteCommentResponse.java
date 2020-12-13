package com.cloudapps.springbooks.model.api.response;

public class DeleteCommentResponse {

private Long id;
	
	private String text;

	private String nick;
	
	private int score;
	
	public DeleteCommentResponse() {
		
	}

	public DeleteCommentResponse(Long id, String text, String nick, int score) {
		super();
		this.id = id;
		this.text = text;
		this.nick = nick;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
