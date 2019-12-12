package com.skillstorm.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Car;
import com.skillstorm.data.CarRepository;

@Service
public class CarService {
	
	private static final Logger log = Logger.getLogger(CarService.class);

	// Autowire the appropriate repository(s) here
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> findAll() {
		log.info("Find all cars");
		return carRepository.findAll();
	}
	
}
