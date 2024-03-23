package com.cos.security1.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security1.model.User;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 이때 로그인 진행이 완료가 되면 시큐리티 (자신만의) session 공간을 만들어준다.(키값으로 구분한다 :: 'Security ContextHolder'라는 키 값에 세션 정보를 저장한다.
// Security Session영역에 저장한다.
// Security Session에 "Authentication" 객체가 있다. 그리고 이 Authentication 객체 안에 유저 정보를 저장할 때 UserDetails 타입이어야 한다.
// Security Session => Authentication => UserDetails 

// 이때 어떻게 UserDetails에 접근하느냐? => implements UserDetails 하면, PrincipalDetails가 UserDetails타입이 된다. 
// 즉,Security Session => Authentication => UserDetails(=PrincipalDetails)
// 그러면 PrincipalDetails를 Authentication 객체 안에 넣을 수 있다.
 
public class PrincipalDetails implements UserDetails {

	// User 정보
	private User user; // 컴포지션
	
	// 생성자
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// 해당 User의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// user.getRole()을 리턴하면 되는데 이는 String타입이기 때문에 Coolection타입으로 바꿔준다.
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority () {

			@Override
			public String getAuthority() {
				return user.getRole();
			}
			
		});
		
		return collect;
	}

	// Password를 리턴하는 곳
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// username 리턴하는 곳
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정만료여부 물어보는 곳 -> 원래 false였는데 true로 바꿈
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정 잠김 여부 물어보는 곳 -> 원래 false였는데 true로 바꿈
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정의 비밀번호가 특정 기간이 지났는지 물어보는 곳 (너무 오래 사용한 건 아닌지) -> 원래 false였는데 true로 바꿈
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화되어있는지 물어보는 곳-> 원래 false였는데 true로 바꿈
	@Override
	public boolean isEnabled() {
		// 언제 false?
		// 우리 사이트에서 회원이 1년동안 로그인을 안 할 경우, 휴면계정으로 전환됨. => private timestamp LoginDate 컬럼을 추가해둔다.
		// 현재시간 - 로그인한 시간(user.getLoginDate();) => 1년을 초과하면 return false
		
		return true;
	}

}
