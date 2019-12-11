package com.skillstorm.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {
	
	@Id
	@Column(name = "TRANSACTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "PRICE")
	private int price;
	@Column(name = "TRANSACTION_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	// Need to figure out relations for all of these
	// private int buyerId;
	// private int sellerId;
	// private Car car;
	
}
