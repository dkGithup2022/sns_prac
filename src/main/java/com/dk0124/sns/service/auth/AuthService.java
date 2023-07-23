package com.dk0124.sns.service.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dk0124.sns.exception.user.EmailNotExistException;
import com.dk0124.sns.exception.user.PasswordNotMatchException;
import com.dk0124.sns.model.user.User;
import com.dk0124.sns.model.user.auth.RefreshToken;
import com.dk0124.sns.repository.user.RefreshTokenRepository;
import com.dk0124.sns.repository.user.UserEntityRepository;
import com.dk0124.sns.util.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserEntityRepository userEntityRepository;
	private final RefreshTokenRepository refreshTokenRepository;

	@Value("${jwt.refreshTokenLifeTime}")
	private Long REFRESH_TOKEN_LIFE_TIME;

	public String login(String email, String password) {
		checkEmailAndPassword(email, password);
		return JwtUtils.publishAccessToken(User.fromEntity(userEntityRepository.findByEmail(email).get()));
	}

	private void checkEmailAndPassword(String email, String password) {
		var user = userEntityRepository.findByEmail(email).orElseThrow(
			() -> new EmailNotExistException(HttpStatus.NO_CONTENT)
		);

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new PasswordNotMatchException(HttpStatus.BAD_REQUEST);
		}
	}

	public String publishRefreshToken(String email) {
		var user = User.fromEntity(userEntityRepository.findByEmail(email)
			.orElseThrow(() -> new EmailNotExistException(HttpStatus.NO_CONTENT))
		);

		String refreshToken = UUID.randomUUID().toString();
		refreshTokenRepository.save(
			new RefreshToken(email, System.currentTimeMillis() + REFRESH_TOKEN_LIFE_TIME, refreshToken));

		return refreshToken;
	}

	public String refreshAccessToken(String token) {
		var refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(
			() -> new RuntimeException("토큰이 없다")
		);

		if (refreshToken.getExpired() < System.currentTimeMillis()) {
			refreshTokenRepository.deleteByToken(token);
			throw new RuntimeException("토큰이 만료되었다");
		}

		refreshToken.setExpired(System.currentTimeMillis() + REFRESH_TOKEN_LIFE_TIME);

		return JwtUtils.publishAccessToken(
			User.fromEntity(
				userEntityRepository.findByEmail(refreshToken.getEmail()).get())
		);
	}

}
