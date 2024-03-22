package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View를 리턴하겠다는 의미!
public class IndexController {
	
	// localhost:8080/
	@GetMapping({"","/"})
	public String index() {
		// 머스테치 사용 -> 머스테치 기본폴더는 src/main/resources/ 로 잡힌다.
		// 뷰리졸버 설정: templates (prefix), .mustache(suffix)  => 생략 가능!
		return "index"; // 찾는 경로: src/main/resources/templates/index.mustache
	}

}
