package com.dk0124.sns.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dk0124.sns.model.user.auth.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
	Optional<RefreshToken> findByToken(String token);

	void deleteByToken(String token);
}
