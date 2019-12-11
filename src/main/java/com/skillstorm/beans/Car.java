package com.skillstorm.beans;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CAR")
public class Car {

	@Id
	@Column(name = "CAR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	// foreign-key to user table
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonBackReference(value = "user-cars")
	private User owner;
	
}
