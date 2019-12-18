package com.skillstorm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.beans.Transaction;
import com.skillstorm.data.CarRepository;
import com.skillstorm.data.TransactionRepository;
import com.skillstorm.data.UserRepository;

@Service
public class TransactionService {
	
	private static final Logger log = Logger.getLogger(TransactionService.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarRepository carRepository;
	
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

	
	public Transaction purchase(Transaction tx) {
		int buyerId = tx.getBuyer().getId();
		int sellerId = tx.getSeller().getId();
		int carId = tx.getCar().getId();
		int price = tx.getPrice();
		
		log.info("Buyer id: " + buyerId);
		log.info("Seller id: " + sellerId);
		
		// get todays date and add it to the transaction object to be saved as a string
		Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
		String currentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(now);
		tx.setDate(currentTime);
		
		// update seller balance ( + price)
		userRepository.increaseBalance(price, sellerId);
		
		// update buyer balance ( - price)
		userRepository.decreaseBalance(price, buyerId);
		
		// update the owner to the buyer
		carRepository.updateOwner(buyerId, carId);
		
		// save the transaction
		transactionRepository.save(tx);
		
		return tx;
	}
}
