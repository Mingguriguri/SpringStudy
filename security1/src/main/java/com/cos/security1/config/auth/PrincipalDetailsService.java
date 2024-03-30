package com.cos.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// 언제 발동?
// 시큐리티 설정에서 .loginProcessingUrl("/login")을 설정했다.
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername이 호출됨(이건 규칙임)

@Service // PrincipalDetailsService가 IoC로 등록됨
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	//before: Security Session => Authentication type => UserDetails type
	//after : Security Session(내부 Authentication type(내부에 UserDetails type))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// userRepository이름으로 user가 있는지 확인해야 함
		User userEntity = userRepository.findByUsername(username);
		
		if (username != null) { // user가 있다면
			return new PrincipalDetails(userEntity); // 리턴하면 UserDetails가 Authentication내부에 들어감+그러면서 Authentication을 Security에 넣어줌 
		} 
		return null;
	}
	
}
