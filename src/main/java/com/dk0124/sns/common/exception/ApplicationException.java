package com.dk0124.sns.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
	private final HttpStatus httpStatus;
	private final String message;
	private final String errorCode;
	private final String detailedMessage;

	public ApplicationException(HttpStatus httpStatus, String detailedMessage) {
		this.httpStatus = httpStatus;
		this.detailedMessage = detailedMessage;
		this.message = ExceptionType.from(this.getClass()).getMessage();
		this.errorCode = ExceptionType.from(this.getClass()).getErrorCode();
	}

	public ApplicationException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.detailedMessage = "";
		this.message = ExceptionType.from(this.getClass()).getMessage();
		this.errorCode = ExceptionType.from(this.getClass()).getErrorCode();
	}
}
