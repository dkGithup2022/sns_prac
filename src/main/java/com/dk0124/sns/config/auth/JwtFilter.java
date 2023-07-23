package com.dk0124.sns.config.auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dk0124.sns.util.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain chain) throws ServletException, IOException {

		var header = request.getHeader(HttpHeaders.AUTHORIZATION);

		try {
			// not valid
			if (header == null || !header.startsWith("Bearer")) {
				log.error("Authorization Header does not start with Bearer {}", request.getRequestURI());
				chain.doFilter(request, response);
				return;
			}


			var token = header.substring(7);
			var claims = JwtUtils.extractClaims(token);

			if (!JwtUtils.validateAccessToken(token)) {
				chain.doFilter(request, response);
				return;
			}

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				claims.get("email", String.class), null,
				List.of(new SimpleGrantedAuthority(claims.get("role", String.class)))
			);



			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}catch (Exception e){
			chain.doFilter(request, response);
			return;
		}
		chain.doFilter(request, response);
	}
}
