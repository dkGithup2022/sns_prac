package com.dk0124.sns.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.dk0124.sns.common.exception.ApplicationException;

public class BadPasswordException extends ApplicationException {
	public BadPasswordException(HttpStatus httpStatus, String detailedMessage) {
		super(httpStatus, detailedMessage);
	}
}
