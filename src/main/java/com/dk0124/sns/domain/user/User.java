package com.dk0124.sns.domain.user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private Long id;
	private String email;
	private String password;
	private Timestamp deletedAt;
	private UserRole userRole;
	private Timestamp createdAt;
	private Timestamp updatedAt;

}
