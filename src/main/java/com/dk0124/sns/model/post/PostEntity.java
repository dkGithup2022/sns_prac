package com.dk0124.sns.model.post;

import java.sql.Timestamp;
import java.util.Objects;

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

import lombok.Builder;
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

	@Setter
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

	@Builder
	public PostEntity(String email, String title, String content, PostType postType) {
		this.email = Objects.requireNonNull(email);
		this.title = Objects.requireNonNull(title);
		this.content = Objects.requireNonNull(content);
		this.postType = Objects.requireNonNull(postType);
	}

}
