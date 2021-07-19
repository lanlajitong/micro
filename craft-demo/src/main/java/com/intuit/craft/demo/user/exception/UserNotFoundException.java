package com.intuit.craft.demo.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends CommonException {
	public UserNotFoundException(String errCode, String message) {
		super(errCode, message);
	}
}
