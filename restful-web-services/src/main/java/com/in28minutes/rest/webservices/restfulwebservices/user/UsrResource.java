package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		User user = service.findOne(id);
		
		if(user==null) {
			throw new UserNotFoundException("id:"+id);
		}
		return user;
	}
	
	// POST /users
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		// /users/{id} => 즉, user.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getId())
							.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
