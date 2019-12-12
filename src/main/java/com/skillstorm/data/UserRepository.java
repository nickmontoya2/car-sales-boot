package com.skillstorm.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("select u from User u inner join u.cars")
//	public List<User> findAll();
	
}
