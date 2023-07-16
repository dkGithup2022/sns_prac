package com.dk0124.sns.controller;

import org.hibernate.mapping.Join;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dk0124.sns.controller.dto.request.JoinRequestDto;
import com.dk0124.sns.controller.dto.request.LoginRequestDto;
import com.dk0124.sns.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/join")
	public ResponseEntity join(@RequestBody JoinRequestDto joinRequestDto) {
		userService.join(joinRequestDto);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity logout() {
		return ResponseEntity.ok().build();
	}
}
