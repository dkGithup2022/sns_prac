package com.dk0124.sns.model.follow;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Follow {
	private Long id;
	private Long fromId;
	private Long toId;
	private Timestamp createdAt;

	public static Follow fromEntity(FollowEntity followEntity) {
		return new Follow(
			followEntity.getId(),
			followEntity.getFromId(),
			followEntity.getToId(),
			followEntity.getCreatedAt()
		);
	}
}
