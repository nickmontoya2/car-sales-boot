package com.skillstorm.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.beans.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
