package com.dk0124.sns.exception.user;

import org.springframework.http.HttpStatus;

import com.dk0124.sns.common.exception.ApplicationException;

public class BadEmailFormatException extends ApplicationException {
	public BadEmailFormatException(HttpStatus httpStatus, String detailedMessage) {
		super(httpStatus, detailedMessage);
	}
}
