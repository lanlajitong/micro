package com.intuit.craft.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.intuit.craft.demo.user.exception.CommonException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends CommonException {
	public UserNotFoundException(String errCode, String message) {
		super(errCode, message);
	}
}
