package com.demo.blogging.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.blogging.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExcepitonHandler(
			ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}
	// for handling duplicate username(email) exception
	@ExceptionHandler(DuplicateUserEmailException.class)
	public ResponseEntity<ApiResponse> handleDuplicateUserEmailException(
			DuplicateUserEmailException ex) {
//		String message = "User Is Already Available With A Given Email Id.. Please Try With Different Email Id..";
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException ex) {

		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

}
