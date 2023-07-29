package com.dk0124.sns.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class SecurityUtils {

	private Authentication getCurrentAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isUserEmailAuthorized(String inputEmail) {
		var authentication = getCurrentAuthentication();
		log.info(authentication.getName());
		if (authentication == null || !authentication.isAuthenticated()) {
			return false;
		}

		return authentication.getName().equals(inputEmail);
	}

	public void validateCurrentEmail(String email) {
		// 에러 생성하기
		if (!SecurityUtils.isUserEmailAuthorized(email))
			throw new RuntimeException("인증 실패");
	}
}
