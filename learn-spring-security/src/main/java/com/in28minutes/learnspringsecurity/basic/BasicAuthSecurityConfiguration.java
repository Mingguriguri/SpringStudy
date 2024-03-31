package com.in28minutes.learnspringsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class BasicAuthSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
						auth -> {
							auth.anyRequest().authenticated();
						});
		
		http.sessionManagement(
						session -> 
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
						); // 로그인, 로그아웃 페이지 모두 없어짐
		
		//http.formLogin() // formLogin을 해제했기 때문에 localhost:8080 들어가면 form은 안 보임
		http.httpBasic();
		
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailService() {
		
		var user = User.withUsername("in28minutes")
			.password("{noop}dummy")
			.roles("USER")
			.build();

		
		var admin = User.withUsername("admin")
				.password("{noop}dummy")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
	
}