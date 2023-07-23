package com.dk0124.sns.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorResponse> catchApplicationException(ApplicationException e) {
		log.error("{}", e);
		var errorBody = buildErrorBody(e);
		return ResponseEntity.status(e.getHttpStatus()).body(errorBody);
	}

	private ErrorResponse buildErrorBody(ApplicationException e) {
		if (e.getDetailedMessage().isBlank())
			return new ErrorResponse(e.getErrorCode(), e.getMessage(), "");

		return new ErrorResponse(e.getErrorCode(), e.getMessage(), e.getDetailedMessage());
	}

	public static record ErrorResponse(String errorCode, String message, String detailedMessage) {
	}
}
