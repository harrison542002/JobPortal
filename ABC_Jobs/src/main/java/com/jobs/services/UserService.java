package com.jobs.services;

import org.springframework.stereotype.Service;

import com.jobs.model.User;
import com.jobs.model.UserDto;

@Service
public class UserService {
	
	public User register(UserDto userdto) {
		
		User user = new User();
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		return user;
	}
	
}
