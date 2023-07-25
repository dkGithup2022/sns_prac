package com.dk0124.sns.model.post;

import java.sql.Timestamp;

import com.dk0124.sns.model.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

	private Long id = null;

	private String title;

	private String content;

	private String email;

	private Timestamp registeredAt;

	private Timestamp updatedAt;

	private Timestamp removedAt;

	public static Post fromEntity(PostEntity entity) {
		return new Post(
			entity.getId(),
			entity.getTitle(),
			entity.getContent(),
			entity.getEmail(),
			entity.getCreatedAt(),
			entity.getUpdatedAt(),
			entity.getDeletedAt()
		);
	}
}
