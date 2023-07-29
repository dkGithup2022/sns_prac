package com.dk0124.sns.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dk0124.sns.controller.dto.request.JoinRequestDto;
import com.dk0124.sns.controller.dto.request.LoginRequestDto;
import com.dk0124.sns.controller.dto.request.RefreshRequestDto;
import com.dk0124.sns.controller.dto.response.LoginResponseDto;
import com.dk0124.sns.controller.dto.response.RefreshResponseDto;
import com.dk0124.sns.service.auth.AuthService;
import com.dk0124.sns.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
		var loginInfo = authService.login(loginRequestDto.email(), loginRequestDto.password());
		return ResponseEntity.ok()
			.body(new LoginResponseDto(loginInfo.id(), loginInfo.accessToken(), loginInfo.refreshToken()));
	}

	@PostMapping("/join")
	public ResponseEntity join(@RequestBody JoinRequestDto joinRequestDto) {
		userService.join(joinRequestDto.email(), joinRequestDto.password(), joinRequestDto.userRole());
		return ResponseEntity.ok().body("Account created");
	}

	@PostMapping("/token/refresh")
	public ResponseEntity refreshAccessToken(@RequestBody RefreshRequestDto refreshRequestDto) {
		var accessToken = authService.refreshAccessToken(refreshRequestDto.refreshToken());
		return ResponseEntity.ok().body(new RefreshResponseDto(accessToken, refreshRequestDto.refreshToken()));
	}

	public ResponseEntity logout() {
		return ResponseEntity.ok().build();
	}

}
