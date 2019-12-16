package com.skillstorm.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.beans.Car;
import com.skillstorm.service.CarService;

@RestController
@RequestMapping(value = "/cars") // Every URI that starts with /cars comes here
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CarController {

	@Autowired
	private CarService carService;
	
	/*
	 * Method to return all FOR_SALE cars in DB
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>> findAllForSale() {
		return new ResponseEntity<List<Car>>(carService.findAllForSale(), HttpStatus.OK);
	}
	
	/*
	 * Return all cars for specific user.
	 * Change this to get {id} from session so userId isn't visible
	 */
	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>> findByUserId(@PathVariable int id) {
		return new ResponseEntity<List<Car>>(carService.findByUserId(id), HttpStatus.OK);
	}
	
	/*
	 * Return specific car
	 */
	@GetMapping(value = "/car/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> findByCarId(@PathVariable int id) {
		Optional<Car> opt = carService.findByCarId(id);
		if(opt.isPresent()) {
			return new ResponseEntity<Car>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Car>(new Car(), HttpStatus.OK);
		}
	}
	
	/*
	 * Allows user to add a new car to the database
	 */
	@PostMapping(value = "/car", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(propagation = Propagation.REQUIRES_NEW) // Create new transaction for this method
	public ResponseEntity<Car> create(@Valid @RequestBody Car car) {
		return new ResponseEntity<Car>(carService.save(car), HttpStatus.CREATED);
	}
	
	/*
	 * Allows user to remove one of their cars from the database
	 */
	@DeleteMapping(value = "/remove/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> remove(@Valid @RequestBody Car car) {
		carService.remove(car);
		return new ResponseEntity<Car>(HttpStatus.OK);
	}
}
