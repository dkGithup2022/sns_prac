package com.dk0124.sns.exception.user;

import org.springframework.http.HttpStatus;

import com.dk0124.sns.common.exception.ApplicationException;

public class EmailNotExistException extends ApplicationException {
	public EmailNotExistException(HttpStatus httpStatus, String detailedMessage) {
		super(httpStatus, detailedMessage);
	}

	public EmailNotExistException(HttpStatus httpStatus) {
		super(httpStatus);
	}
}
