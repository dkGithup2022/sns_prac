package com.dk0124.sns.common.exception;

import java.util.Arrays;
import java.util.Objects;

import com.dk0124.sns.exception.user.BadEmailFormatException;
import com.dk0124.sns.exception.user.BadPasswordException;
import com.dk0124.sns.exception.user.EmailAlreadyExistException;
import com.dk0124.sns.exception.user.PasswordNotMatchException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionType {

	// user domain
	EMAIL_ALREADY_EXIST_EXCEPTION("1001", "이메일이 이미 있습니다.", EmailAlreadyExistException.class),
	BAD_EMAIL_FORMAT_EXCEPTION("1002", "이메일 형식이 맞지 않습니다.", BadEmailFormatException.class),
	BAD_PASSWORD_FORMAT_EXCEPTION("1003", "패스워드 형식이 맞지 않습니다. ", BadPasswordException.class),
	PASSWORD_NOT_MATCH_EXCEPTION("1004", "패스워드가 틀립니다", PasswordNotMatchException.class),

	// unhandled one
	UNHANDLED_EXCEPTION("9999", "처리되지 않은 에러 ", UnhandledException.class),
	;

	private String errorCode;
	private String message;
	private Class<? extends ApplicationException> type;

	public static ExceptionType from(Class<?> classType) {
		return Arrays.stream(values())
			.filter(it -> Objects.nonNull(it.type) && it.type.equals(classType))
			.findFirst()
			.orElse(UNHANDLED_EXCEPTION);
	}

}
