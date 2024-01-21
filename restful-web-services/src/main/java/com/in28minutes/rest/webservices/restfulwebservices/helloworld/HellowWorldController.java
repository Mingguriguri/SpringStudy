package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldController {
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
}
