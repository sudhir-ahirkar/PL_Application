package com.dkatalis.parkinglot.assignment.dao.impl;

import java.util.List;

import com.dkatalis.parkinglot.assignment.dao.IParkingLotDataManagement;
import com.dkatalis.parkinglot.assignment.model.Vehicle;

public class IParkingLotDataManagementImpl<T extends Vehicle> implements IParkingLotDataManagement<T> {

	public int parkCar(T vehicle) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean leaveCar(int slotNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getRegNumberForColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> getSlotNumbersFromColor(String colour) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getSlotNoFromRegistrationNo(String registrationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getAvailableSlotsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void doCleanUp() {
		// TODO Auto-generated method stub

	}

}
