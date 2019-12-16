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
	
	public List<Transaction> findByUserId(int id) {
		log.info("Finding all transactions involving userId: " + id);
		return transactionRepository.findByUserId(id);
	}
	
}
