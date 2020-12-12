package com.cloudapps.springbooks.model.api.request;

public class PostUserRequest {
	
	private String nick;
	
	private String email;
	
	public PostUserRequest() {
		
	}

	public PostUserRequest(String nick, String email) {
		this.nick = nick;
		this.email = email;
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
