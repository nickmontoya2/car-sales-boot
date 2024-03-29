package com.skillstorm.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.beans.User;
import com.skillstorm.service.UserService;

@RestController
@RequestMapping(value = "/users") // Every URI that starts with /users
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Method to return all users in the DB
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUser(@RequestBody User creds, HttpServletResponse response){
		// checks passed in credentials and passes the username and password to the login service. 
		ResponseEntity<User> user = new ResponseEntity<User>(userService.loginUser(creds.getUsername(), creds.getPassword()), HttpStatus.OK);
		
		// if the user is found, return all of that user's information and save their userId in a cookie. If they are not found, return an empty object.
		if(user.hasBody()) {
			Cookie userIdCookie = new Cookie("userId", String.valueOf(user.getBody().getId()));
			userIdCookie.setMaxAge(1_800);
			response.addCookie(userIdCookie);
			return user;
		}
		else return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
}
