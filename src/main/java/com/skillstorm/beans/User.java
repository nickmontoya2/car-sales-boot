package com.skillstorm.beans;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "USER")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User {
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("userId")
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "USERNAME")
	private String username;
	
//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	@Transient 
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "USER_BALANCE")
	private int balance;
	
	@OneToMany(mappedBy = "owner")
	// @JsonManagedReference(value = "user-cars")
	@JsonIgnore
	private List<Car> cars;
	
	/*
	 *  This code is inspired by a similar situation found on SO
	 *  relating teams to matches played as home or away
	 */
	
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
	//@JsonManagedReference(value = "user-buyer")
	@JsonIgnore
	private Set<Transaction> buyerTransactions;
	
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	//@JsonManagedReference(value = "user-seller")
	@JsonIgnore
	private Set<Transaction> sellerTransactions;
	
	public User() {
		super();
		// Patrick prefers empty lists to start instead of null
		cars = new LinkedList<>();
		buyerTransactions = new HashSet<>();
		sellerTransactions = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Set<Transaction> getBuyerTransactions() {
		return buyerTransactions;
	}

	public void setBuyerTransactions(Set<Transaction> buyerTransactions) {
		this.buyerTransactions = buyerTransactions;
	}

	public Set<Transaction> getSellerTransactions() {
		return sellerTransactions;
	}

	public void setSellerTransactions(Set<Transaction> sellerTransactions) {
		this.sellerTransactions = sellerTransactions;
	}
	
	// Leaving out cars & transactions to avoid recursive issues
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", balance="
				+ balance + "]";
	}

	// Make method to return list of all transactions
//	public Set<Transaction> getAllTransactions() {
//		Set<Transaction> allTransactions = new HashSet<Transaction>();
//		allTransactions.addAll(buyerTransactions);
//		allTransactions.addAll(sellerTransactions);
//		return allTransactions;
//	}
//	
}
