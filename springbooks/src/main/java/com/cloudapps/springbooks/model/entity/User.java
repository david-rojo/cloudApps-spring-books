package com.cloudapps.springbooks.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	public static User EXISTING_NICK = new User(
			"Invalid nick. This nick exists in database", "");
	
	public static User USER_WITH_COMMENTS = new User(
			"The requested user has comments, is not possible to delete it. Please remove his comments first", "");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nick;
	
	private String email;
	
	protected User() {
		
	}
	
	public User(String nick, String email) {
		super();
		this.nick = nick;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", "
				+ "nick" + nick + ", "
				+ "email=" + email + "]";
	}
}
