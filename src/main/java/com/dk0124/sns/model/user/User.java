package com.dk0124.sns.model.user;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@RedisHash("User")
public class User {
	@Id
	private Long id;
	@Indexed
	private String email;
	private String password;
	private Timestamp deletedAt;
	private UserRole userRole;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public static User fromEntity(UserEntity entity) {
		return new User(
			entity.getId(),
			entity.getEmail(),
			entity.getPassword(),
			entity.getDeletedAt(),
			entity.getUserRole(),
			entity.getCreatedAt(),
			entity.getUpdatedAt()
		);
	}

}
