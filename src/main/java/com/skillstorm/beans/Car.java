package com.skillstorm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "CAR")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Car {

	@Id
	@Column(name = "CAR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("carId")
	private int id;
	
	@Column(name = "MAKE")
	private String make;
	
	@Column(name = "MODEL")
	private String model;
	
	@Column(name = "YEAR")
	private int year;
	
	@Column(name = "VALUE")
	private int value;
	
	@Column(name = "MILEAGE")
	private int mileage;
	
	@Column(name = "FUEL")
	private String fuel;
	
	@Column(name = "EXTERIOR_COLOR")
	private String exteriorColor;
	
	@Column(name = "INTERIOR_COLOR")
	private String interiorColor;
	
	@Column(name = "TRANSMISSION")
	private String transmission;
	
	@Column(name = "ENGINE")
	private String engine;
	
	@Column(name = "DRIVE_TYPE")
	private String driveType;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SALE_STATUS")
	@Enumerated(EnumType.STRING)
	private SalesStatus saleStatus;
	
	@Column(name = "PHOTO_LINK")
	private String photoLink;
	
	// User who owns this car
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	// @JsonBackReference(value = "user-cars")
	private User owner;
	
	// List of transactions this car has been in
	@OneToMany(mappedBy = "car")
	// @JsonManagedReference(value = "car-transactions")
	@JsonIgnore
	private Set<Transaction> transactions;
	
	public Car() {
		super();
		// Patrick prefers empty lists to start instead of null
		transactions = new HashSet<>();
	}
	
	// constructor for saving a new car directly from the front end
	public Car(String make, String model, int year, int value, int mileage, String fuel, String exteriorColor,
		String interiorColor, String transmission, String engine, String driveType, String description,
		SalesStatus saleStatus, String photoLink, User owner) {
	super();
	this.make = make;
	this.model = model;
	this.year = year;
	this.value = value;
	this.mileage = mileage;
	this.fuel = fuel;
	this.exteriorColor = exteriorColor;
	this.interiorColor = interiorColor;
	this.transmission = transmission;
	this.engine = engine;
	this.driveType = driveType;
	this.description = description;
	this.saleStatus = saleStatus;
	this.photoLink = photoLink;
	this.owner = owner;
	transactions = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getExteriorColor() {
		return exteriorColor;
	}

	public void setExteriorColor(String exteriorColor) {
		this.exteriorColor = exteriorColor;
	}

	public String getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SalesStatus getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(SalesStatus saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	// Leaving out owner & transactions to avoid recursive issues
	@Override
	public String toString() {
		return "Car [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", value=" + value
				+ ", mileage=" + mileage + ", fuel=" + fuel + ", exteriorColor=" + exteriorColor + ", interiorColor="
				+ interiorColor + ", transmission=" + transmission + ", engine=" + engine + ", driveType=" + driveType
				+ ", description=" + description + ", saleStatus=" + saleStatus + ", photoLink=" + photoLink + "]";
	}
}
