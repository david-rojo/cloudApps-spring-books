package com.cloudapps.springbooks.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;

}
