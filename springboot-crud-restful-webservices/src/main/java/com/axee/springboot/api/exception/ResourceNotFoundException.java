package com.axee.springboot.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  String  message;
	private  String  errorReason;
	
	
	public ResourceNotFoundException(String message, String errorReason) {
		super(message);
		this.message = message;
		this.errorReason = errorReason;
	}


	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getErrorReason() {
		return errorReason;
	}
	
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}
}


