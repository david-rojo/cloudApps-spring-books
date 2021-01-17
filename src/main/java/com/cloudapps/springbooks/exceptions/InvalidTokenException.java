package com.cloudapps.springbooks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Invalid token")
public class InvalidTokenException extends RuntimeException {

	private static final long serialVersionUID = 8830505542763317441L;

}
