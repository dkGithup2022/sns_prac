package com.dk0124.sns.model.user;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.dk0124.sns.common.data.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATE \"user\" SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at is NULL")
public class UserEntity extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	@Setter
	private String email;
	@Setter
	private String password;
	@Setter
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	private Timestamp deletedAt;

	public static UserEntity of(String email, String password, UserRole userRole){
		var userEntity = new UserEntity();
		userEntity.setEmail(Objects.requireNonNull(email));
		userEntity.setPassword(Objects.requireNonNull(password));
		userEntity.setUserRole(userRole);
		return userEntity;
	}

}
