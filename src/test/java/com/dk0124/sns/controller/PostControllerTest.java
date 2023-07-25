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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.dk0124.sns.controller.dto.request.CreatePostRequestDto;
import com.dk0124.sns.controller.dto.request.ModifyPostRequestDto;
import com.dk0124.sns.model.post.Post;
import com.dk0124.sns.model.post.PostEntity;
import com.dk0124.sns.model.post.PostType;
import com.dk0124.sns.service.post.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

	private static final String POST_API_PREFIX = "/api/v1/post";

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	PostService postService;

	@Test
	@WithMockUser(value = "email", username = "email")
	public void 생성_성공() throws Exception {

		var req = new CreatePostRequestDto("email", "title", "body", PostType.NORMAL);
		var body = objectMapper.writeValueAsString(req);

		mvc.perform(post(POST_API_PREFIX + "/create").content(body))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	@WithAnonymousUser
	public void 생성_실패_인증정보_없음() throws Exception {

		var req = new CreatePostRequestDto("email", "title", "body", PostType.NORMAL);
		var body = objectMapper.writeValueAsString(req);

		doThrow(new RuntimeException()).when(postService)
			.create(
				req.email(),
				req.tile(),
				req.content(),
				req.postType()
			);

		// 403 unAuthorized code check plz
		mvc.perform(
				post(POST_API_PREFIX + "/create")
					.contentType(MediaType.APPLICATION_JSON)
					.content(body))
			.andExpect(status().is4xxClientError())
			.andDo(print());
	}

	// 실패 테스트는 필터 에러 핸들러 만들고 하기 ... ㅎ;
	@Test
	@WithMockUser(value = "email", username = "email")
	public void 업데이트_성공() throws Exception {
		var id = 1L;
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;

		var req = new ModifyPostRequestDto(title, content, postType);
		var body = objectMapper.writeValueAsString(req);

		when(postService.modify(id, title, content, postType)).thenReturn(
			Post.fromEntity(PostEntity.of("email", title, content, postType))
		);

		mvc.perform(
				put(POST_API_PREFIX + "/" + id)
					.contentType(MediaType.APPLICATION_JSON)
					.content(body))
			.andExpect(status().isOk())
			.andDo(print());
	}

}