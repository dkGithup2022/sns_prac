package com.dk0124.sns.service.auth;

public record LoginSuccessDto(Long id, String accessToken, String refreshToken) {
}
