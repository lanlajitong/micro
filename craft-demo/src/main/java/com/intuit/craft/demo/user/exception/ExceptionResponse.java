package com.intuit.craft.demo.user.exception;

import java.util.Date;
import java.util.List;

public class ExceptionResponse {

	private Date timestmap;
	private String errCode;
	private String message;
	private String details;
	List<FieldDetailWithFixer> fields;
	
	public ExceptionResponse(Date timestmap, String message, String details) {
		super();
		this.timestmap = timestmap;
		this.message = message;
		this.details = details;
	}
	public ExceptionResponse(Date timestmap, String errCode, String message, String details) {
		this(timestmap, message, details);
		this.errCode = errCode;
	}
	public ExceptionResponse(Date timestmap, String errCode, String message, String details, List<FieldDetailWithFixer> fields) {
		this(timestmap, errCode, message, details);
		this.fields = fields;
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
	public List<FieldDetailWithFixer> getFields() {
		return fields;
	}
	public void setFields(List<FieldDetailWithFixer> fields) {
		this.fields = fields;
	}
	
	
}
