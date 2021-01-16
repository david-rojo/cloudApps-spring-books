package com.cloudapps.springbooks.dtos.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Comment is mandatory")
    private String comment;
    @Min(value = 0, message = "Score must be equals or greater than 0")
    @Max(value = 5, message = "Score must be equals or less than 5")
    private float score;

}
