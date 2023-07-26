package com.dk0124.sns.repository.follow;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dk0124.sns.model.follow.Follow;
import com.dk0124.sns.model.follow.FollowEntity;

public interface FollowEntityRepository extends JpaRepository<FollowEntity, Long> {

	Page<FollowEntity> findByFromId(Pageable pageable, Long fromId);

	Page<FollowEntity> findByToId(Pageable pageable, Long toId);


	List<FollowEntity> findByFromId(Long fromId);


	List<FollowEntity> findByToId(Long toId);

}
