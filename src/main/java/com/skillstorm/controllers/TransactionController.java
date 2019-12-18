package com.skillstorm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.beans.Transaction;
import com.skillstorm.service.TransactionService;

@RestController
@RequestMapping(value = "/tx")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	/*
	 * Return list of purchases for a user
	 */
	@GetMapping(value = "/user-purchases/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> findPurchasesByUserId(@PathVariable int id) {
		return new ResponseEntity<List<Transaction>>(transactionService.findPurchasesByUserId(id), HttpStatus.OK);
	}
	
	/*
	 * Return list of sales for a user
	 */
	@GetMapping(value = "/user-sales/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> findSalesByUserId(@PathVariable int id) {
		return new ResponseEntity<List<Transaction>>(transactionService.findSalesByUserId(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<Transaction> executePurchase(@RequestBody Transaction tx /* , HttpServletResponse response */) {

		ResponseEntity<Transaction> transaction = new ResponseEntity<Transaction>(transactionService.purchase(tx), HttpStatus.OK);

		if (!transaction.hasBody())
			return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Transaction>(HttpStatus.OK);
		
		// can return transaction here if desired. See TransactionService
	}
}
