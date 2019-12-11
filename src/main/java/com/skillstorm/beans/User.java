package com.skillstorm.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "USER_BALANCE")
	private int balance;
	
	@OneToMany(mappedBy = "owner")
	@JsonManagedReference(value = "user-cars")
	private List<Car> cars;
	
	/*
	 *  This code is inspired by a similar situation I found on SO
	 *  relating teams to matches played as home or away
	 */
	
	@OneToMany(mappedBy = "buyer")
	@JsonManagedReference(value = "user-buyer")
	private List<Transaction> buyerTransactions;
	
	@OneToMany(mappedBy = "seller")
	@JsonManagedReference(value = "user-seller")
	private List<Transaction> sellerTransactions;
	
	// Make method to return list of all transactions
	
}
