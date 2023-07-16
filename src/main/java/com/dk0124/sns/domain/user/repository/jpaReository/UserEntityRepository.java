package com.dk0124.sns.domain.user.repository.jpaReository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dk0124.sns.domain.user.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
}
