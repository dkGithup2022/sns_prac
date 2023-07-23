package com.dk0124.sns.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk0124.sns.exception.user.EmailAlreadyExistException;
import com.dk0124.sns.model.user.UserEntity;
import com.dk0124.sns.model.user.UserRole;
import com.dk0124.sns.repository.user.UserEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserEntityRepository userEntityRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void join(String email, String password, UserRole role) {

		userEntityRepository.findByEmail(email).ifPresent(
			it -> {
				throw new EmailAlreadyExistException(HttpStatus.CONFLICT);
			}
		);
		userEntityRepository.save(UserEntity.of(email, passwordEncoder.encode(password), role));
	}

}
