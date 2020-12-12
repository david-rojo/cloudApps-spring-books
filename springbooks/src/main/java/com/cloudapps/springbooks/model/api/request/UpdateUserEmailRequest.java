package com.cloudapps.springbooks.model.api.request;

public class UpdateUserEmailRequest {
	
	private String email;
	
	public UpdateUserEmailRequest() {
		
	}
	
	public UpdateUserEmailRequest(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
