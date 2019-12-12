package com.skillstorm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.User;
import com.skillstorm.data.UserRepository;

@Service
public class UserService {

	// Autowire the appropriate repository(s) here
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
