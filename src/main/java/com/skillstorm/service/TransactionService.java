package com.skillstorm.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Transaction;
import com.skillstorm.data.TransactionRepository;

@Service
public class TransactionService {
	
	private static final Logger log = Logger.getLogger(TransactionService.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> findPurchasesByUserId(int id) {
		log.info("Finding all purchases by userId: " + id);
		return transactionRepository.findPurchasesByUserId(id);
	}

	public List<Transaction> findSalesByUserId(int id) {
		log.info("Finding all sales by userId: " + id);
		return transactionRepository.findSalesByUserId(id);
	}
	
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}
	
}
