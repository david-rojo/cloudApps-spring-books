package com.cloudapps.springbooks.model.api.response;

public class PostCommentResponse {

	public static PostCommentResponse NON_EXISTENT_NICK_COMMENT = new PostCommentResponse(null, 
			"Any user in the system has this nick, the comment must be associated to an existing nick. "
			+ "Please verify it", "", 0);
	
	public static PostCommentResponse INVALID_SCORE_COMMENT = new PostCommentResponse(null,
			"Invalid score. Valid scores: 0, 1, 2, 3, 4, 5", "", 0);
	
	private Long id;
	
	private String text;

	private String nick;
	
	private int score;
	
	public PostCommentResponse() {
		
	}

	public PostCommentResponse(Long id, String text, String nick, int score) {
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
