package com.dkatalis.parkinglot.assignment.dao;

import java.util.List;

import com.dkatalis.parkinglot.assignment.model.Vehicle;

public interface IParkingLotDataManagement<T extends Vehicle> {

	public int parkCar(T vehicle);

	public boolean leaveCar(int slotNumber);

	public List<String> getStatus();

	public List<String> getRegNumberForColor(String color);

	public List<Integer> getSlotNumbersFromColor(String colour);

	public int getSlotNoFromRegistrationNo(String registrationNo);

	public int getAvailableSlotsCount();

	public void doCleanUp();
}
