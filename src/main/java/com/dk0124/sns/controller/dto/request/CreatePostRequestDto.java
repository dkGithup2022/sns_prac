package com.dk0124.sns.controller.dto.request;

import com.dk0124.sns.model.post.PostType;

public record CreatePostRequestDto(String email, String tile, String content, PostType postType) {
}
