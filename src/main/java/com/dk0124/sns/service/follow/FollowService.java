package com.dk0124.sns.service.follow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dk0124.sns.model.follow.Follow;
import com.dk0124.sns.model.follow.FollowEntity;
import com.dk0124.sns.repository.follow.FollowEntityRepository;
import com.dk0124.sns.repository.user.UserEntityRepository;
import com.dk0124.sns.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {

	private final FollowEntityRepository followRepository;
	private final UserEntityRepository userRepository;

	public Long create(Long fromId, Long toId) {
		var from = userRepository.findById(fromId)
			.orElseThrow(() -> new RuntimeException("아이디 없음"));

		SecurityUtils.validateCurrentEmail(from.getEmail());

		return followRepository.save(
			FollowEntity.builder().fromId(fromId).toId(toId).build()
		).getId();
	}

	public void delete(Long id) {
		var follow = followRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("팔로우 없음"));
		followRepository.delete(follow);
	}

	public Page<Follow> listFollower(Pageable pageable, Long toId) {
		return followRepository.findByToId(pageable, toId).map(Follow::fromEntity);
	}

	public Page<Follow> listFollowing(Pageable pageable, Long fromId) {
		return followRepository.findByFromId(pageable, fromId).map(Follow::fromEntity);
	}

}
