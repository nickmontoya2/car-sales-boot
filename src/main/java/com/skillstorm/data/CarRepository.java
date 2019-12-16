package com.skillstorm.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.beans.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	@Query("select c from Car c where c.saleStatus = 'FOR_SALE'")
	public List<Car> findCars();
	
	public List<Car> findByOwnerId(int id);
	
	// update the for sale status of a car
	@Modifying
	@Query("update Car set status = ?1 where id = ?2")
	public void updateCarStatus(String status, int id);
}
