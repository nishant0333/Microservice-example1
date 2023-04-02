package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {
	
	//user Operation
	
	
	//create
	
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	
	//get single user of given userId
	
	 User getUser(String userId);
	 
	// User deleteUser(String userId);
	 
	 // user updateUser(User user , String userId);
	
	

}
