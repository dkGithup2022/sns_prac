package com.dk0124.sns.model.follow;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 등록 -> 삭제 -> 다시 등록 이 유연해야함으로  hard delete
@Entity
@Table(name = "\"follow\"")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter

/**
 *  from, to 각각 인덱스, (from, to )조합에 대한 복합 unique 조건
 */
public class FollowEntity {
	@Id
	@GeneratedValue
	private Long id;

	// JPA 스타일로 해야할까?
	private Long fromId;

	private Long toId;

	@CreatedDate
	private Timestamp createdAt;

	@Builder
	public FollowEntity(Long fromId, Long toId) {
		this.fromId = Objects.requireNonNull(fromId);
		this.toId = Objects.requireNonNull(toId);
	}
}
