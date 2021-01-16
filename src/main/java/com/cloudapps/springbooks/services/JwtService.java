package com.cloudapps.springbooks.services;

import com.cloudapps.springbooks.dtos.responses.WhoamiResponseDto;

public interface JwtService {

	public WhoamiResponseDto getUser(String authHeader);	
	
	public boolean isValid(String authHeader);
	
}
