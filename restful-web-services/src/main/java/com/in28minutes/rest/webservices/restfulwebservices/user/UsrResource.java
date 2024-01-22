package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsrResource {

	private UserDaoService service;
	
	public UsrResource(UserDaoService service) {
		this.service=service;
	}
	
	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();		
	}
	
	// GET /users/{id}
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		return service.findOne(id);
	}
	
	// POST /users
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		service.save(user);
	}
}
