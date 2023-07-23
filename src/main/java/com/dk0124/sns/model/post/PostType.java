package com.dk0124.sns.model.post;

import java.util.Arrays;

import com.dk0124.sns.model.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostType {

	NORMAL("NORMAL"), NOTICE("NOTICE"), ETC("ETC");

	String type;

	public static PostType from(String type) {
		return
			Arrays.stream(PostType.values()).filter(
					it -> it.type.equals(type)
				)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("맞는 타입이 없음"));
	}
}
