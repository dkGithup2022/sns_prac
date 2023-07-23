package com.dk0124.sns.model.user.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(name = "token_idx", columnList = "token")})
public class RefreshToken {

	@Id
	@Column(name = "email")
	private String email;

	/*
	@OneToOne
	@PrimaryKeyJoinColumn(name = "email", referencedColumnName = "email")
	private UserEntity userEntity;
	*/

	@Setter
	@Column(nullable = false, unique = true)
	private Long expired;

	@Column(nullable = false)
	private String token;

	public RefreshToken(String email, Long expired, String token) {
		this.expired = expired;
		this.email = email;
		this.token = token;
	}

}
