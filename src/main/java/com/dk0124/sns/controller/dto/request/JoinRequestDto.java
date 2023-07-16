package com.dk0124.sns.controller.dto.request;

import javax.validation.constraints.NotBlank;

import com.dk0124.sns.domain.user.UserRole;

public record JoinRequestDto(
	@NotBlank String email,
	@NotBlank String password,
	UserRole userRole) {

}
