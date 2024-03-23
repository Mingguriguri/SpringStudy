package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller // View를 리턴하겠다는 의미!
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// localhost:8080/
	@GetMapping({"","/"})
	public String index() {
		// 머스테치 사용 -> 머스테치 기본폴더는 src/main/resources/ 로 잡힌다.
		// 뷰리졸버 설정: templates (prefix), .mustache(suffix)  => 생략 가능!
		return "index"; // 찾는 경로: src/main/resources/templates/index.mustache
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	// 스프링시큐리티 해당주소를 낚아채버림!! -> SecurityConfig 파일 생성 후, 작동 안함
	@GetMapping("/loginForm") // 로그인할 수 있는 Form
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm") // 회원가입할 수 있는 Form
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join") // 실제 회원가입 프로세스
	public String join(User user) {
		System.out.println(user); // 잘 찍히는지 확인
		user.setRole("ROLE_USER");// user의 ROLE을 강제로 넣어준다.
		// Id와 createDate는 자동으로 만들어진다.
		
		// 비밀번호 암호화
		String rawPassword = user.getPassword(); // 원래 비밀번호
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 암호화
		user.setPassword(encPassword); // 저장
		
		userRepository.save(user);
		
		return "redirect:/loginForm"; // 회원가입이 정상적으로 완료될 경우, 리다이렉트로 loginForm으로 넘어가도록 함
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터정보";
	}
	

}
