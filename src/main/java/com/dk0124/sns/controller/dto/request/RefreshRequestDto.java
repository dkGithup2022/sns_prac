package com.dk0124.sns.controller.dto.request;

import javax.validation.constraints.NotBlank;

public record RefreshRequestDto(@NotBlank String refreshToken) {
}
