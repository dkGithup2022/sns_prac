package com.dk0124.sns.controller;

import javax.annotation.security.PermitAll;

import org.springframework.http.ResponseEntity;
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
import com.dk0124.sns.service.post.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/create")
	public ResponseEntity create(CreatePostRequestDto createPostRequestDto) {
		var post = postService.create(
			createPostRequestDto.email(),
			createPostRequestDto.tile(),
			createPostRequestDto.content(),
			createPostRequestDto.postType()
		);
		return ResponseEntity.ok().body(new CreatePostResponseDto());
	}

	@PermitAll
	@GetMapping("/{id}")
	public ResponseEntity findOne(@PathVariable Long id) {
		var post = postService.findOne(id);
		return ResponseEntity.ok().body(post);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		postService.delete(id);
		return ResponseEntity.ok().body("delete completed");
	}

	@PutMapping("/{id}")
	public ResponseEntity modify(@PathVariable Long id, @RequestBody ModifyPostRequestDto modifyPostRequestDto) {
		var post = postService.modify(
			id,
			modifyPostRequestDto.title(),
			modifyPostRequestDto.content(),
			modifyPostRequestDto.postType()
		);
		return ResponseEntity.ok().body(post);
	}
}
