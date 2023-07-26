package com.dk0124.sns.controller;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dk0124.sns.controller.dto.request.FollowRequest;
import com.dk0124.sns.service.follow.FollowService;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
public class FollowController {

	private final FollowService followService;

	@PostMapping
	public ResponseEntity follow(@RequestBody FollowRequest followRequest) {
		var followId = followService.create(followRequest.fromId(), followRequest.toId());
		return ResponseEntity.ok().body(new JSONPObject("follow id", followId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		// check user authenticated
		followService.delete(id);
		return ResponseEntity.ok().body("deleted");
	}

	@GetMapping("/follower")
	public ResponseEntity listFollower(Pageable pageable, Long id) {
		return ResponseEntity.ok().body(followService.listFollower(pageable, id));
	}

	@GetMapping("/following")
	public ResponseEntity listFollowing(Pageable pageable, Long id) {
		return ResponseEntity.ok().body(followService.listFollowing(pageable, id));
	}
}
