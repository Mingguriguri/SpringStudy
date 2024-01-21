package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowWorldController {
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	// Path Parameters
	// /users/{id}/todos/{id} => /users/1/todos/101
	// hello-world/path-variable/{name}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(
				String.format("Hello World, %s", name));
	}
}
