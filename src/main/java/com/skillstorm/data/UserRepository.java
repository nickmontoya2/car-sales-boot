package com.skillstorm.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("select u from User u inner join u.cars")
//	public List<User> findAll();

	@Query("from User where username = ?1 and password = ?2 ")
	public User findByUsernameAndPass(String username, String password);

	@Modifying
	@Query("update User set balance = balance + ?1 where id = ?2 ")
	public void increaseBalance(int amount, int id);

	@Modifying
	@Query("update User set balance = balance - ?1 where id = ?2 ")
	public void decreaseBalance(int amount, int id);
}
