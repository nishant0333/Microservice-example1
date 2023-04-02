package com.lcwd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;



import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	

	@Autowired
	private UserService userService;
	
	//create
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User user1 = userService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);	
	}
	
	//single user get
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		
		User user = userService.getUser(userId);
		
		return ResponseEntity.ok(user);
		}
	
	
	public ResponseEntity<User> ratingHotelFallback(String userId , Exception ex){
		log.info("fallback is executed because service is down :", ex.getMessage());
	
		User user = User.builder()
		.email("Dummy@gmail.com")
		.name("Dummy")
		.about("This user is created Dummy because some service is down")
		.userid("1234567")
		.build();
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	
	//all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		
		List<User> allUser = userService.getAllUser();
		
		return ResponseEntity.ok(allUser);
	}
}
