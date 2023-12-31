package com.dk0124.sns.controller;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dk0124.sns.controller.dto.request.CreatePostRequestDto;
import com.dk0124.sns.controller.dto.request.ModifyPostRequestDto;
import com.dk0124.sns.controller.dto.response.CreatePostResponseDto;
import com.dk0124.sns.model.post.Post;
import com.dk0124.sns.service.post.PostService;
import com.dk0124.sns.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/create")
	public ResponseEntity create(@RequestBody CreatePostRequestDto createPostRequestDto) {
		var post = postService.create(
			createPostRequestDto.email(),
			createPostRequestDto.tile(),
			createPostRequestDto.content(),
			createPostRequestDto.postType()
		);
		return ResponseEntity.ok().body(new CreatePostResponseDto(post.getId()));
	}

	@PermitAll
	@GetMapping("/list")
	public ResponseEntity list(Pageable pageable) {
		return ResponseEntity.ok().body(new PageImpl(postService.list(pageable)));
	}

	@PermitAll
	@GetMapping("/{id}")
	public ResponseEntity findOne(@PathVariable Long id) {
		return ResponseEntity.ok().body(postService.findOne(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		postService.delete(id);
		return ResponseEntity.ok().body("delete completed");
	}

	@PutMapping("/{id}")
	public ResponseEntity modify(@PathVariable Long id, @RequestBody ModifyPostRequestDto modifyPostRequestDto, Authentication authentication) {

		var post = postService.modify(
			id,
			modifyPostRequestDto.title(),
			modifyPostRequestDto.content(),
			modifyPostRequestDto.postType()
		);
		return ResponseEntity.ok().body(post);
	}


	private void validateCurrentEmail(String email) {
		// 에러 생성하기
		if (!SecurityUtils.isUserEmailAuthorized(email))
			throw new RuntimeException("인증 실패");
	}

}
