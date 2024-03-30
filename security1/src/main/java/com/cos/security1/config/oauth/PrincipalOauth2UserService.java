package com.cos.security1.config.oauth;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.config.oauth.provider.GoogleUserInfo;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료 시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	    System.out.println("OAuth2 로그인 시도: " + userRequest.getClientRegistration().getRegistrationId());

		System.out.println("getClientRegistration:"+userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인했는지 확인가능
		System.out.println("getAccessToken:"+userRequest.getAccessToken());
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		// userRequest 정보: 구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> 코드 리턴(Oauth-Client라이브러리)-> 액세스 토큰 요청
        // userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필을 받아준다.
		System.out.println("getAttributes:"+oauth2User.getAttributes());

		
		
		// 회원가입 강제 진행
		String provider = userRequest.getClientRegistration().getClientId(); // google
		 // 뒤에 진행할 다른 소셜 서비스 로그인을 위해 구분 => 구글
		String providerId = oauth2User.getAttribute("sub");
		String username = provider + "_" + providerId; // google_103058387739722400130
		//String password = bCryptPasswordEncoder.encode("겟인데어"); // 필요 없지만 그냥 만들어주는 것
		String password = "겟인데어"; // 필요 없지만 그냥 만들어주는 것
		String email = oauth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		// 이미 회원인지 여부 확인
		User findUser = userRepository.findByUsername(username);
		User user;
		System.out.println("일단 여기까진 왔음");
		if (findUser == null) { // 못 찾았을 경우, 강제로 회원가입
			System.out.println("구글 로그인이 최초입니다.");
			user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId)
					.build();
		    userRepository.save(user);
			
		}
		else {
			user = findUser;
			System.out.println("구글 로그인을 이미 한 적이 있습니다. 당신은 자동 회원가입이 되어 있습니다.");
		}
		
		return new PrincipalDetails(user, oauth2User.getAttributes());
	}
}
