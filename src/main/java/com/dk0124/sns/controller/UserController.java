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

	// TODO : login 서비스 나누는게 조금 이상하다고 생각됨.
	// ㄴ> login 의 결과랑 response 가 같은데, service 를 위한 dto 가 필요한가 ?
	// 일단 지금은 직관성이 좀 떨어짐..
	// 그리고 RDMS 쓰면 여기에 transactional 붙여도 될듯?
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {

		String accessToken = authService.login(loginRequestDto.email(), loginRequestDto.password());
		String refreshToken = authService.publishRefreshToken(loginRequestDto.email());

		return ResponseEntity.ok().body(new LoginResponseDto(accessToken, refreshToken));
	}

	@PostMapping("/join")
	public ResponseEntity join(@RequestBody JoinRequestDto joinRequestDto) {
		userService.join(joinRequestDto.email(), joinRequestDto.password(), joinRequestDto.userRole());
		return ResponseEntity.ok().body("Account created");
	}

	@PostMapping("/token/refresh")
	public ResponseEntity refreshAccessToken(@RequestBody RefreshRequestDto refreshRequestDto) {

		System.out.println("REQ: " + refreshRequestDto);
		var accessToken = authService.refreshAccessToken(refreshRequestDto.refreshToken());
		return ResponseEntity.ok().body(new RefreshResponseDto(accessToken, refreshRequestDto.refreshToken()));
	}

	public ResponseEntity logout() {
		return ResponseEntity.ok().build();
	}

}
