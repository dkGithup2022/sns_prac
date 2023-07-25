package com.dk0124.sns.service.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.dk0124.sns.model.post.Post;
import com.dk0124.sns.model.post.PostEntity;
import com.dk0124.sns.model.post.PostType;
import com.dk0124.sns.repository.post.PostEntityRepository;

@SpringBootTest
class PostServiceTest {

	@Autowired
	PostService postService;

	@MockBean
	PostEntityRepository postRepository;

	@Test
	@WithMockUser(value = "email", username = "email")
	public void 저장_성공() {
		var email = "email";
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;
		var postEntity = PostEntity.builder().email(email).title(title).content(content).postType(postType).build();

		when(postRepository.save(postEntity)).thenReturn(postEntity);

		postService.create(email, title, content, postType);
	}

	@Test
	@WithMockUser(value = "email", username = "wrong auth")
	public void 저장_실패_인증정보_다름() {
		var email = "email";
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;
		var postEntity = PostEntity.builder().email(email).title(title).content(content).postType(postType).build();

		when(postRepository.save(postEntity)).thenReturn(postEntity);
		assertThrows(RuntimeException.class, () -> postService.create(email, title, content, postType));
	}

	@Test
	public void 하나_읽기_성공() {
		var id = 1L;
		var email = "email";
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;
		var postEntity = PostEntity.builder().email(email).title(title).content(content).postType(postType).build();

		when(postRepository.findById(id)).thenReturn(Optional.of(postEntity));
		assertEquals(postEntity.getContent(), postService.findOne(id).getContent());
	}

	@Test
	@WithMockUser(value = "email", username = "email")
	public void 지우기_성공() {
		var id = 1L;
		var email = "email";
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;
		var postEntity = PostEntity.builder().email(email).title(title).content(content).postType(postType).build();

		when(postRepository.findById(id)).thenReturn(Optional.of(postEntity));
		doNothing().when(postRepository).deleteById(id);
	}

	@Test
	@WithMockUser(value = "email", username = "i'm on attack")
	public void 지우기_실패_유저정보_틀림() {
		var id = 1L;
		var email = "email";
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;
		var postEntity = PostEntity.builder().email(email).title(title).content(content).postType(postType).build();

		when(postRepository.findById(id)).thenReturn(Optional.of(postEntity));

		assertThrows(RuntimeException.class, () -> postService.delete(id));
	}

	@Test
	@WithMockUser(value = "email", username = "email")
	public void 지우기_실패_없는_포스트() {
		var id = 1L;
		when(postRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> postService.delete(id));
	}

	@Test
	@WithMockUser(value = "email", username = "email")
	public void 업데이트_성공() {
		var id = 1L;
		var email = "email";
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;
		var postEntity = PostEntity.builder().email(email).title(title).content(content).postType(postType).build();

		var titleChanged = "title changed";
		var contentChanged = "content changed";

		when(postRepository.findById(id)).thenReturn(Optional.of(postEntity));

		Post post = postService.modify(id, titleChanged, contentChanged, postType);

		assertEquals(titleChanged, post.getTitle());
		assertEquals(contentChanged, post.getContent());
	}

	@Test
	@WithMockUser(value = "email", username = "i'm on attack")
	public void 업데이트_실패_인증정보_다름() {
		var id = 1L;
		var email = "email";
		var title = "title";
		var content = "content";
		var postType = PostType.NORMAL;
		var postEntity = PostEntity.builder().email(email).title(title).content(content).postType(postType).build();

		when(postRepository.findById(id)).thenReturn(Optional.of(postEntity));

		assertThrows(RuntimeException.class, () -> postService.modify(id, title, content, postType));
	}

}