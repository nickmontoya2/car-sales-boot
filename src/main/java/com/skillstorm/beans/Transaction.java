package com.skillstorm.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_ID")
	@JsonBackReference(value = "car-transactions")
	private Car car;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonBackReference(value = "user-buyer")
	private User buyer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonBackReference(value = "user-seller")
	private User seller;
	
}
