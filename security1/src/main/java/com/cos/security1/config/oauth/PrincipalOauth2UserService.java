package com.cos.security1.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료 시  @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// userRequest 정보: 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> 코드 리턴(Oauth-Client라이브러리)-> 액세스 토큰 요청
        // userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필을 받아준다.
		
		System.out.println("getClientRegistration:"+userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인했는지 확인가능
		System.out.println("getAccessToken:"+userRequest.getAccessToken());
		System.out.println("getAccessToken.getTokenValue:"+userRequest.getAccessToken().getTokenValue());
		System.out.println("getClientId:"+userRequest.getClientRegistration().getClientId());
		System.out.println("getAttributes:"+super.loadUser(userRequest).getAttributes());

		
		OAuth2User oauth2User = super.loadUser(userRequest);
		// 회원가입 강제 진행

		return super.loadUser(userRequest);
	}
}
