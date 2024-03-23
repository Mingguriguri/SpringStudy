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
			.requestMatchers("/user/**").authenticated() // authenticated(): 인증 완료해야 접근 가능
			.requestMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //인증 후, admin이나 manager권한 있어야 함
			.requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //인증 후, admin이나 manager권한 있어야 함
			.anyRequest().permitAll(); // anyRequest(): 그 외 나머지 리소스들, permitAll(): 설정한 리소스의 접근을 인증절차 없이 허용한다는 의미

		return http.build();
	}
}
