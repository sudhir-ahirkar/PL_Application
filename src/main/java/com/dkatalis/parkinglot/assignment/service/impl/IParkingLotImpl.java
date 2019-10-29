package com.dkatalis.parkinglot.assignment.service.impl;

import java.util.Optional;

import com.dkatalis.parkinglot.assignment.exception.ParkingException;
import com.dkatalis.parkinglot.assignment.model.Vehicle;
import com.dkatalis.parkinglot.assignment.service.IParkingLot;

public class IParkingLotImpl implements IParkingLot {

	public void createParkingLot(int level, int capacity) throws ParkingException {
		// TODO Auto-generated method stub

	}

	public Optional<Integer> park(int level, Vehicle vehicle) throws ParkingException {
		// TODO Auto-generated method stub
		return null;
	}

	public void unPark(int level, int slotNumber) throws ParkingException {
		// TODO Auto-generated method stub

	}

	public void getStatus(int level) throws ParkingException {
		// TODO Auto-generated method stub

	}

	public Optional<Integer> getAvailableSlotsCount(int level) throws ParkingException {
		// TODO Auto-generated method stub
		return null;
	}

	public void getRegNumberForColor(int level, String color) throws ParkingException {
		// TODO Auto-generated method stub

	}

	public void getSlotNumbersFromColor(int level, String colour) throws ParkingException {
		// TODO Auto-generated method stub

	}

	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void doCleanup() {
		// TODO Auto-generated method stub

	}

}
