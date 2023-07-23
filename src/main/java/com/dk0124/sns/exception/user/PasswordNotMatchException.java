package com.dk0124.sns.exception.user;

import org.springframework.http.HttpStatus;

import com.dk0124.sns.common.exception.ApplicationException;

public class PasswordNotMatchException extends ApplicationException {
	public PasswordNotMatchException(HttpStatus httpStatus, String detailedMessage) {
		super(httpStatus, detailedMessage);
	}

	public PasswordNotMatchException(HttpStatus httpStatus) {
		super(httpStatus);
	}
}
