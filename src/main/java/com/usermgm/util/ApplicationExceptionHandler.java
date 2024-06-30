package com.usermgm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.usermgm.exception.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus statuscode, String errorMessage,
			String rootCause) {
		return ResponseEntity.status(statuscode).body(new ErrorStructure<String>().setStatuscode(statuscode.value())
				.setErrorMessage(errorMessage).setRootCause(rootCause));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> userNotFoundException(UserNotFoundException userNotFoundException) {
		return errorResponse(HttpStatus.NOT_FOUND, userNotFoundException.getMessage(),
				"The user is not found by the given id");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<Map<String, String>>> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {
		List<ObjectError> objErrors = ex.getAllErrors();

//		List<String> AllErrors = allErrors.stream().map(error -> {
//			FieldError fieldError = (FieldError) error;
//			return fieldError.getDefaultMessage();
//		}).toList();

		Map<String, String> allErrors = new HashMap<>();
		objErrors.forEach(error -> {
			FieldError fieldError = (FieldError) error;
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			allErrors.put(field, message);
		});

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorStructure<Map<String, String>>().setStatuscode(HttpStatus.NOT_FOUND.value())
						.setErrorMessage("Invalid Input").setRootCause(allErrors));
	}
}
