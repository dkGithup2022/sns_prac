package com.dk0124.sns.controller.dto.request;

import com.dk0124.sns.model.post.PostType;

public record ModifyPostRequestDto(String title, String content, PostType postType) { }
