package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

@ExceptionHandler(Exception.class)
public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
	ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), 
			ex.getMessage(), request.getDescription(false));
	
	return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	
	}
}
 