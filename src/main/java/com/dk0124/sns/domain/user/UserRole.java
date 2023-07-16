package com.dk0124.sns.domain.user;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

	USER("USER"), ADMIN("ADMIN"), TEMP("TEMP");

	String role;

	public static UserRole from(String role) {
		return
			Arrays.stream(UserRole.values()).filter(
					it -> it.role.equals(role)
				)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("맞는 유저롤이 없음"));
	}
}
