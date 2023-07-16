package com.dk0124.sns.common.exception;

import org.springframework.http.HttpStatus;

public class UnhandledException extends ApplicationException{
	public UnhandledException(HttpStatus httpStatus, String message) {
		super(httpStatus, message);
	}
}
