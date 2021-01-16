package com.cloudapps.springbooks.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WhoamiResponseDto {

	private String username;
	
}
