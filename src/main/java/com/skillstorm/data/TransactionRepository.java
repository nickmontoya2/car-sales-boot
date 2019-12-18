package com.skillstorm.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.beans.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("select tx from Transaction tx where tx.buyer.id = ?1")
	List<Transaction> findPurchasesByUserId(int id);

	@Query("select tx from Transaction tx where tx.seller.id = ?1")
	List<Transaction> findSalesByUserId(int id);

}

// tx.id, tx.price, tx.date, tx.car.id, tx.buyer.id, tx.seller.id