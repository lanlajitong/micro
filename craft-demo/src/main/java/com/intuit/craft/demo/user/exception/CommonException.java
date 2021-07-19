package com.intuit.craft.demo.user.exception;

public class CommonException extends RuntimeException {
	protected final String errCode;

	public CommonException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}
}
