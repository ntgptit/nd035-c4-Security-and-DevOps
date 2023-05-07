package com.example.demo.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author GiapNT
 *
 */
@ControllerAdvice
public class Course4ExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * Handle internal server error.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler({ InternalServerError.class, Exception.class })
	protected ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {

		ErrorResponse body = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getCause());

		return this.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(Course4Exception.class)
	protected ResponseEntity<Object> handleCourse4Exception(Course4Exception ex, WebRequest request) {
		ErrorResponse body = new ErrorResponse(ex.getMessage(), HttpStatus.PRECONDITION_FAILED,
				HttpStatus.PRECONDITION_FAILED.value(), ex.getCause());
		return this.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
	}

	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
		ErrorResponse body = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
				ex.getCause());
		return this.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Data
	@AllArgsConstructor
	static class ErrorResponse {
		private String error;
		private HttpStatus status;
		private int statusCode;
		private Object data;
	}
}
