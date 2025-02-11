package com.parkauto.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.parkauto.rest.entity.HttpResponse;

@RestControllerAdvice
public class ExceptionHandling {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration.";
	private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send '%s' request.";
	private static final String INTERNAL_SERVER_ERROR_MSG = "An error occured while processing the request.";
	private static final String INCORRECT_CREDENTIALS = "Username / password incorrect, please try again.";
	private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration.";
	private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file.";
	private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission.";
	private static final String ERROR_PATH = "/error";
	
	public ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		
		return new ResponseEntity<>(new HttpResponse(
				httpStatus.value(),
				httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(),
				message.toUpperCase()), 
				httpStatus);
	}
}
