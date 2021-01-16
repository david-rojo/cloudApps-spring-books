package com.cloudapps.springbooks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User with same username exists")
public class UserWithSameUsernameException extends RuntimeException {

	private static final long serialVersionUID = -5763510899245664744L;
}
