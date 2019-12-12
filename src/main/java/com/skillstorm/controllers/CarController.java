package com.skillstorm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.beans.Car;
import com.skillstorm.service.CarService;

@RestController
@RequestMapping(value = "/cars") // Every URI that starts with /cars comes here
public class CarController {

	@Autowired
	private CarService carService;
	
	// Method to return all cars in DB
	@GetMapping
	public ResponseEntity<List<Car>> findAll() {
		// Call service method to return all cars here
		return new ResponseEntity<List<Car>>(carService.findAll(), HttpStatus.OK);
	}
	
	/*
	 * Return all cars for specific user.
	 * Change this to get {id} from session so userId isn't visible
	 * 
	 */
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<List<Car>> findByUserId(@PathVariable int id) {
		return new ResponseEntity<List<Car>>(carService.findByUserId(id), HttpStatus.OK);
	}
}
