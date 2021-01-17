package com.cloudapps.springbooks.services;

import com.cloudapps.springbooks.dtos.responses.UserResponseDto;

public interface JwtService {

	public UserResponseDto getUser(String authHeader);	
	
	public boolean isValid(String authHeader);
	
}
