package com.lawencon.lmsjosepvictor.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.lmsjosepvictor.dto.ErrorResDto;
import com.lawencon.lmsjosepvictor.exception.CustomException;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResDto<String>> handleNullPointerException(NullPointerException npe) {
		final ErrorResDto<String> response = new ErrorResDto<>();
		response.setMessage("Error! Please fill all the data!");
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResDto<String>> handleBadCredentials(BadCredentialsException bce) {
		final ErrorResDto<String> response = new ErrorResDto<>();
		response.setMessage("Error! Wrong username / password!");
		
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResDto<List<String>>> handleMethoArgumentNotValidException(MethodArgumentNotValidException exception) {
		final List<String> errorResponses = exception
				.getBindingResult().getFieldErrors()
				.stream()
				.map(v -> v.getDefaultMessage())
				.collect(Collectors.toList());
		
		final ErrorResDto<List<String>> responses = new ErrorResDto<>();
		responses.setMessage(errorResponses);
		return new ResponseEntity<>(responses, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(com.lawencon.lmsjosepvictor.exception.CustomException.class)
	public ResponseEntity<ErrorResDto<String>> handleTicketLimitException(CustomException exception){
		final ErrorResDto<String> response = new ErrorResDto<>();
		response.setMessage(exception.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
