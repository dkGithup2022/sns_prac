package com.dk0124.sns.config.auth.userDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dk0124.sns.repository.user.UserEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserEntityRepository userEntityRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		var user = userEntityRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("유저를 찾을 수 없음"));

		return null;
	}
}
