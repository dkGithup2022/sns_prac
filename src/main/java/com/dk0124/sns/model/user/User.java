package com.dk0124.sns.model.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
	private Long id;
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
