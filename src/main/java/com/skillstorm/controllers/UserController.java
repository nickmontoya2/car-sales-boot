package com.skillstorm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.beans.User;
import com.skillstorm.service.UserService;

@RestController
@RequestMapping(value = "/users") // Every URI that starts with /users
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Method to return all users in the DB
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
}
