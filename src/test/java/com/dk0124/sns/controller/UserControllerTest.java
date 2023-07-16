package com.dk0124.sns.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dk0124.sns.controller.dto.request.JoinRequestDto;
import com.dk0124.sns.controller.dto.request.LoginRequestDto;
import com.dk0124.sns.domain.user.UserRole;
import com.dk0124.sns.domain.user.exception.EmailAlreadyExistException;
import com.dk0124.sns.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	UserService userService;

	private final static String USER_API_BASE = "/api/v1/user";

	@Test
	public void 회원가입_성공() throws Exception {

		// given
		var id = "Hello";
		var password = "world";

		var loginApiUrl = "/join";
		var loginReq = new JoinRequestDto(id, password, UserRole.USER);
		var body = objectMapper.writeValueAsString(loginReq);

		doNothing().when(userService).join(loginReq);

		// when then
		mvc.perform(post(USER_API_BASE + loginApiUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)
			).andDo(print())
			.andExpect(status().isOk());

	}

	@Test
	public void 회원가입_실패_존재하는_이름() throws Exception {

		var id = "Hello";
		var password = "world";

		var loginApiUrl = "/join";
		var loginReq = new JoinRequestDto(id, password, UserRole.USER);
		var body = objectMapper.writeValueAsString(loginReq);

		doThrow(new EmailAlreadyExistException(HttpStatus.BAD_REQUEST)).when(userService).join(loginReq);

		mvc.perform(post(USER_API_BASE + loginApiUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)
			).andDo(print())
			.andExpect(status().isBadRequest());

	}

	public void 회원가입_실패_형식이_틀린_아이디() {

	}

	public void 로그인_성공() {

	}

	public void 로그인_실패_비밀번호_틀림() {

	}

	public void 로그인_실패_비밀번호_포맷() {

	}

}