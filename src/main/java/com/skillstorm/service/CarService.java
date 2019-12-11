package com.skillstorm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Car;
import com.skillstorm.data.CarRepository;

@Service
public class CarService {

	// Autowire the appropriate repository(s) here
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> findAll() {
		return carRepository.findAll();
	}
	
}