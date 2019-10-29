package com.dkatalis.parkinglot.assignment.service;

import java.util.Optional;

import com.dkatalis.parkinglot.assignment.exception.ParkingException;
import com.dkatalis.parkinglot.assignment.model.Vehicle;

public interface IParkingLot {

	public void createParkingLot(int level, int capacity) throws ParkingException;

	public Optional<Integer> park(int level, Vehicle vehicle) throws ParkingException;

	public void unPark(int level, int slotNumber) throws ParkingException;

	public void getStatus(int level) throws ParkingException;

	public Optional<Integer> getAvailableSlotsCount(int level) throws ParkingException;

	public void getRegNumberForColor(int level, String color) throws ParkingException;

	public void getSlotNumbersFromColor(int level, String colour) throws ParkingException;

	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingException;

	public void doCleanup();
	
	public static final String	CREATE_PARKING_LOT					= "create_parking_lot";
	public static final String	PARK								= "park";
	public static final String	LEAVE								= "leave";
	public static final String	STATUS								= "status";
	public static final String	REG_NUMBER_FOR_CARS_WITH_COLOR		= "registration_numbers_for_cars_with_colour";
	public static final String	SLOTS_NUMBER_FOR_CARS_WITH_COLOR	= "slot_numbers_for_cars_with_colour";
	public static final String	SLOTS_NUMBER_FOR_REG_NUMBER			= "slot_number_for_registration_number";
	
	public static final int	NOT_AVAILABLE			= -1;
	public static final int	VEHICLE_ALREADY_EXIST	= -2;
	
	public static final int NOT_FOUND = -1;
}
