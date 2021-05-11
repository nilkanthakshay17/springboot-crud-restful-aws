package com.axee.springboot.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.axee.springboot.api.model.GlobalError;



@RestControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public ResponseEntity<?> handelResourceNotFoundException(ResourceNotFoundException rnfe){
		GlobalError ge=new GlobalError();
		ge.setMessage(rnfe.getMessage());
		ge.setErrorReason(rnfe.getErrorReason());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ge);
	}
}
