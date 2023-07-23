package com.dk0124.sns.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dk0124.sns.model.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private static Key KEY;
	private static Long ACCESS_TOKEN_LIFE_TIME;


	@Value("${jwt.accessTokenLifetime}")
	public void setAccessTokenLifeTime(Long accessTokenLifetime) {
		JwtUtils.ACCESS_TOKEN_LIFE_TIME = accessTokenLifetime;
	}

	@Value("${jwt.secret}")
	public void setKey(String secret) {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		JwtUtils.KEY = Keys.hmacShaKeyFor(keyBytes);
	}

	public static boolean validateAccessToken(String accsessToken) {
		return new Date(System.currentTimeMillis()).before(extractClaims(accsessToken).getExpiration());
	}

	public static Claims extractClaims(String accessToken) {
		return Jwts.parserBuilder().setSigningKey(KEY)
			.build().parseClaimsJws(accessToken)
			.getBody();
	}

	public static String publishAccessToken(User user) {
		var claims = Jwts.claims();
		claims.put("email", user.getEmail());
		claims.put("role", user.getUserRole().getRole());

		// 이슈 시간을 Timestamp 로 변경.
		return Jwts.builder()
			.signWith(KEY)
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_LIFE_TIME))
			.compact();
	}
}
