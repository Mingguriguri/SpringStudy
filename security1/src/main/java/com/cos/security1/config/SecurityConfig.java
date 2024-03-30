package com.cos.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.security1.config.oauth.PrincipalOauth2UserService;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 위 활성화 ⇒ 이를 활성화하면 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다. 
//특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize&postAuthorize 어노테이션 활성화
public class SecurityConfig {

	// Oauth 관련
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	// @Bean을 적으면 -> 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(cs-> cs.disable());
		
		http
		.authorizeHttpRequests((authz) -> authz
			.requestMatchers("/user/**").authenticated() // authenticated(): 인증 완료해야 접근 가능
			.requestMatchers("/manager/**").hasAnyAuthority("ROLE_ADMIN","ROLE_MANAGER") //인증 후, admin이나 manager권한 있어야 함
			.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")//인증 후, admin 권한 있어야 함
			.anyRequest().permitAll()); // anyRequest(): 그 외 나머지 리소스들, permitAll(): 설정한 리소스의 접근을 인증절차 없이 허용한다는 의미
		
		http.formLogin(form -> form // 로그인 페이지와 기타 로그인 처리 및 성공 실패 처리를 사용하겠다는 의미
			.loginPage("/loginForm") // 사용자가 따로 만든 로그인 페이지를 사용하려고 할때 설정
			.loginProcessingUrl("/login") //로그인 즉 인증 처리를 하는 URL을 설정. 
										//해당 URL이 호출되면 시큐리티가 낚아채서 대신 로그인 인증처리를 수행. 
										// 따라서 Controller에 /login을 만들지 않아도 된다.
			.defaultSuccessUrl("/")); // 정상적으로 인증성공 했을 경우 이동하는 페이지
		
		http.oauth2Login(oauth2 -> oauth2 // oauth
			.loginPage("/loginForm") // 구글로그인이 완료된 후, 후처리가 필요
								// !Tip! 구글 로그인이 완료가 되면 코드 X, 액세스토큰+사용자프로필 정보를 한방에 받음
			.userInfoEndpoint(userInfo -> userInfo
					.userService(principalOauth2UserService)));
		
		return http.build();
	}
}
