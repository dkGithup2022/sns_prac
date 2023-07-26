package com.dk0124.sns.model.alarm;

import java.util.Arrays;

import com.dk0124.sns.model.post.PostType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AlarmType {

	NEW_POST("newPost"), LIKE_POST("likePost"), NEW_COMMENT("newComment");

	String value;

	public static AlarmType from(String type) {
		return
			Arrays.stream(AlarmType.values()).filter(
					it -> it.value.equals(type)
				)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("맞는 타입이 없음"));
	}
}
