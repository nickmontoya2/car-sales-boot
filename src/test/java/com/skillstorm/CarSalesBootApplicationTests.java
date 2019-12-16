package com.skillstorm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skillstorm.beans.Car;
import com.skillstorm.data.CarRepository;

@SpringBootTest
class CarSalesBootApplicationTests {

	private static final Logger log = Logger.getLogger(CarSalesBootApplicationTests.class);
	
	@Autowired
	private CarRepository repo;
	
	@Test
	void contextLoads() {
		log.info("IN TEST CASE");
		List<Car> cars = repo.findByOwnerId(2);
		log.info(cars);
		for (Car car : cars) {
			log.info(car.getOwner());
		}
		assertEquals(1, cars.size());
	}

}
