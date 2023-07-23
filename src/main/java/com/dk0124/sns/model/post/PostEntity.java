package com.dk0124.sns.model.post;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.dk0124.sns.common.data.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@SQLDelete(sql = "UPDATE  post  SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at is NULL")
@NoArgsConstructor
public class PostEntity extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Setter // insert, update 간결하게 하려고 일부러 없앰 .
	private String email;

	@Setter
	private String title;

	@Setter
	@Column(columnDefinition = "TEXT")
	private String content;

	@Setter
	@Enumerated(EnumType.STRING)
	private PostType postType;

	private Timestamp deletedAt = null;

	// 강의 보고 따라하는데, 이 방식이 setter 가 많아서 구려보임
	public static PostEntity of(String email, String title, String content, PostType postType) {
		PostEntity entity = new PostEntity();
		entity.setEmail(email);
		entity.setTitle(title);
		entity.setContent(content);
		entity.setPostType(postType);
		return entity;
	}
}
