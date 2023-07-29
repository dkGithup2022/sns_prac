package com.dk0124.sns.service.post;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk0124.sns.model.alarm.AlarmEntity;
import com.dk0124.sns.model.alarm.AlarmType;
import com.dk0124.sns.model.follow.FollowEntity;
import com.dk0124.sns.model.post.Post;
import com.dk0124.sns.model.post.PostEntity;
import com.dk0124.sns.model.post.PostType;
import com.dk0124.sns.repository.alarm.AlarmEntityRepository;
import com.dk0124.sns.repository.follow.FollowEntityRepository;
import com.dk0124.sns.repository.post.PostEntityRepository;
import com.dk0124.sns.repository.user.UserEntityRepository;
import com.dk0124.sns.service.follow.FollowService;
import com.dk0124.sns.util.SecurityUtils;

import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostEntityRepository postRepository;
	private final UserEntityRepository userRepository;
	private final FollowEntityRepository followEntityRepository;
	private final AlarmEntityRepository alarmRepository;

	@Transactional
	public Post create(String email, String title, String content, PostType postType) {
		validateCurrentEmail(email);


		/* 유저를 팔로우한 유저 찾아서 알람 생성  */
		/* 이 부분을 이벤트로 날려버리고 싶음 */
		var user = userRepository.findByEmail(email).get();
		var followers = followEntityRepository.findByToId(user.getId());

		for (var follower : followers)
			alarmRepository.save(
				AlarmEntity.builder().toId(follower.getFromId()).alarmType(AlarmType.NEW_POST).build());

		var post = postRepository.save(
			PostEntity.builder().email(email).title(title).content(content).postType(postType).build()
		);

		return Post.fromEntity(post);
	}

	public Post findOne(Long id) {
		//포스트 낫 파운드  익셉션
		return Post.fromEntity(postRepository.findById(id).orElseThrow(
			() -> new RuntimeException("포스트 없음")
		));
	}

	public List<Post> list(Pageable pageable) {
		return postRepository.findAll(pageable).stream().map(Post::fromEntity).toList();
	}

	/*
	TODO : DSL 쿼리 미리 기록
	public List<Post> search(Pageable pageable, PostType postType, Sor)
	 */

	public void delete(Long id) {
		var post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("포스트 없음"));

		validateCurrentEmail(post.getEmail());
		postRepository.deleteById(id);
	}

	@Transactional
	public Post modify(Long id, String title, String content, PostType postType) {
		var originalEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("포스트 없음"));

		validateCurrentEmail(originalEntity.getEmail());

		originalEntity.setTitle(title);
		originalEntity.setContent(content);
		originalEntity.setPostType(postType);

		return Post.fromEntity(originalEntity);
	}

	private void validateCurrentEmail(String email) {
		// 에러 생성하기
		if (!SecurityUtils.isUserEmailAuthorized(email))
			throw new RuntimeException("인증 실패");
	}

}
