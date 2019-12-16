package com.skillstorm.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.beans.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
