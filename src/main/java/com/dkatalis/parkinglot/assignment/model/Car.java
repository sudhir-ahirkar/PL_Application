package com.dkatalis.parkinglot.assignment.model;

import com.dkatalis.parkinglot.assignment.constant.VehicleColor;

public class Car extends Vehicle {

	private int neededSpace = 3;
	
//	public Car(String registrationNo, VehicleColor color) {
//		super(registrationNo, color);
//		// TODO Auto-generated constructor stub
//	}
	
	public Car(String registrationNo, String color) {
		super(registrationNo, color);
		// TODO Auto-generated constructor stub
	}

	public int getNeededSpace() {
		return neededSpace;
	}

	public void setNeededSpace(int neededSpace) {
		this.neededSpace = neededSpace;
	}
	

}
