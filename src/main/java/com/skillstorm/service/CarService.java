package com.skillstorm.service;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Car;
import com.skillstorm.beans.SalesStatus;
import com.skillstorm.beans.Transaction;
import com.skillstorm.data.CarRepository;
import com.skillstorm.data.TransactionRepository;
import com.skillstorm.data.UserRepository;

@Service
public class CarService {
	
	private static final Logger log = Logger.getLogger(CarService.class);
	
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserRepository userRepository;

	
	public List<Car> findAllForSale() {
		log.info("Find all cars that are for sale");
		return carRepository.findCars();
	}
	
	public List<Car> findByUserId(int id) {
		log.info("Finding all cars for userId: " + id);
		return carRepository.findByOwnerId(id);
	}
	
	public Optional<Car> findByCarId(int id) {
		log.info("Finding specific car with id: " + id);
		return carRepository.findById(id);
	}
	
	public Car save(Car car) {
		log.info("Saving new car");
		log.info(car.getOwner().getId());
		car.setOwner(userRepository.findById(car.getOwner().getId()).get());
		log.info(car.getOwner());
		
		
		// if new car doesn't have any transactions built in
		if (car.getTransactions() == null || car.getTransactions().isEmpty()) {
			return carRepository.save(car);
		} else { // if new car has transactions make sure to save them 
			log.info("Saving transactions associated with new car");
			Car result = carRepository.save(car);
			
			for(Transaction tx : car.getTransactions()) {
				transactionRepository.save(tx);
			}
			return result;
		}
	} // End save()

	public void remove(int id) {
		log.info("Deleting car");
		Car car = carRepository.findById(id).get();
		carRepository.delete(car);
	} // remove()

	public void setNewCarStatus(String status, int id) {
		if (status.equals("FOR_SALE")) {
			carRepository.updateCarStatus(SalesStatus.FOR_SALE, id);
		} else {
			carRepository.updateCarStatus(SalesStatus.NOT_FOR_SALE, id);
		}
		
	}
	
}
