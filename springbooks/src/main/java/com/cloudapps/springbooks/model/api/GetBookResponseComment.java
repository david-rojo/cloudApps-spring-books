package com.cloudapps.springbooks.model.api;

public class GetBookResponseComment {
	
	private String text;
	
	private String nick;
	
	private String email;
	
	
	public GetBookResponseComment() {
		
	}
	
	public GetBookResponseComment(String text, String nick, String email) {
		this.text = text;
		this.nick = nick;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
