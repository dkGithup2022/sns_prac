package com.dk0124.sns.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dk0124.sns.model.post.PostEntity;

public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
}
