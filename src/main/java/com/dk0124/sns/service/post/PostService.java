package com.dk0124.sns.service.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dk0124.sns.model.post.Post;
import com.dk0124.sns.model.post.PostEntity;
import com.dk0124.sns.model.post.PostType;
import com.dk0124.sns.repository.post.PostEntityRepository;
import com.dk0124.sns.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostEntityRepository postRepository;

	public Post create(String email, String title, String content, PostType postType) {
		validateCurrentEmail(email);

		var post = postRepository.save(PostEntity.of(email, title, content, postType));
		return Post.fromEntity(post);
	}

	public Post findOne(Long id) {
		//포스트 낫 파운드  익셉션
		return Post.fromEntity(postRepository.findById(id).orElseThrow(
			() -> new RuntimeException("포스트 없음")
		));
	}

	public void list() {
	} // should be dsl query

	public void delete(Long id) {
		var post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("포스트 없음"));

		validateCurrentEmail(post.getEmail());
		postRepository.findById(id);
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
