package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 위활성화 ⇒ 이를 활성화하면 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다. 
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http
		.authorizeRequests()
			.requestMatchers("/user/**").authenticated()
			.requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			.requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll();

		return http.build();
	}
}
