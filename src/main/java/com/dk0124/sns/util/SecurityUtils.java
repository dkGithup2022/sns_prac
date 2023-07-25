package com.dk0124.sns.util;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtils {

	private Authentication getCurrentAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean isUserEmailAuthorized(String inputEmail) {
		var authentication = getCurrentAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return false;
		}

		return authentication.getName().equals(inputEmail);
	}
}
