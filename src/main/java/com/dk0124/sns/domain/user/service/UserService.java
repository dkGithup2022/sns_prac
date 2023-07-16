package com.dk0124.sns.domain.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk0124.sns.controller.dto.request.JoinRequestDto;
import com.dk0124.sns.domain.user.entity.UserEntity;
import com.dk0124.sns.domain.user.exception.EmailAlreadyExistException;
import com.dk0124.sns.domain.user.repository.jpaReository.UserEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserEntityRepository userEntityRepository;

	@Transactional
	public void join(JoinRequestDto joinRequestDto) {
		userEntityRepository.findByEmail(joinRequestDto.email()).orElseThrow(
			() -> new EmailAlreadyExistException(HttpStatus.BAD_REQUEST, "이메일이 이미 있어요" + joinRequestDto.email())
		);

		userEntityRepository.save(UserEntity.of(
			joinRequestDto.email(),
			joinRequestDto.password(),
			joinRequestDto.userRole()
		));
	}

	private void login() {

	}
}
