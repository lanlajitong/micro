package com.intuit.craft.demo.user.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timestmap;
	private String errCode;
	private String message;
	private String details;
	
	
	public ExceptionResponse(Date timestmap, String message, String details) {
		super();
		this.timestmap = timestmap;
		this.message = message;
		this.details = details;
	}
	public ExceptionResponse(Date timestmap, String errCode, String message, String details) {
		super();
		this.timestmap = timestmap;
		this.errCode = errCode;
		this.message = message;
		this.details = details;
	}
	public Date getTimestmap() {
		return timestmap;
	}
	public void setTimestmap(Date timestmap) {
		this.timestmap = timestmap;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	
}
