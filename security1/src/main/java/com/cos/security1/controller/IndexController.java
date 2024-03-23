package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View를 리턴하겠다는 의미!
public class IndexController {
	
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
	
	@GetMapping("/join") // 실제 회원가입 프로세스
	public @ResponseBody String join() {
		return "join";
	}
	

}
