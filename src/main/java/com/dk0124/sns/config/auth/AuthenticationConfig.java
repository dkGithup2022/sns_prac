package com.dk0124.sns.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		web
			.ignoring()
			.antMatchers("/css/**", "/js/**", "/img/**")
			.antMatchers("/api-docs/**", "/swagger-ui/**")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/v1/user/login", "/api/v1/user/join", "/api/v1/user/token/refresh").permitAll()
			.antMatchers("/api/v1/post/list").permitAll()
			.antMatchers("/api/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

		;
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

}
