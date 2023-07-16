package com.dk0124.sns.domain.user.exception;

import org.springframework.http.HttpStatus;

import com.dk0124.sns.common.exception.ApplicationException;

public class EmailAlreadyExistException extends ApplicationException {
	public EmailAlreadyExistException(HttpStatus errerCode, String detailedMessage) {

		super(errerCode, detailedMessage);
	}

	public EmailAlreadyExistException(HttpStatus errerCode) {

		super(errerCode, null);
	}

}
