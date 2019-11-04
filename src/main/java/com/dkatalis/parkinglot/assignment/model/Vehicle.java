package com.dkatalis.parkinglot.assignment.model;

import com.dkatalis.parkinglot.assignment.constant.VehicleSize;

public abstract class Vehicle {

	private String registrationNo = null;
//	private VehicleColor color = null;
	private String color = null;
	private VehicleSize size = null;
	
	
//	public Vehicle(String registrationNo, VehicleColor color) {
//		super();
//		this.registrationNo = registrationNo;
//		this.color = color;
//	}
	
	public Vehicle(String registrationNo, String color) {
		super();
		this.registrationNo = registrationNo;
		this.color = color;
	}
	
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
//	public VehicleColor getColor() {
//		return color;
//	}
//	public void setColor(VehicleColor color) {
//		this.color = color;
//	}
	public VehicleSize getSize() {
		return size;
	}
	public void setSize(VehicleSize size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
