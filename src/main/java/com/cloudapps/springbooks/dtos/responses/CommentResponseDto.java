package com.cloudapps.springbooks.dtos.responses;

import lombok.Data;

@Data
public class CommentResponseDto {

    private Long id;
    private CommentUserResponseDto user;
    private String comment;
    private float score;

}
