package com.dkatalis.parkinglot.assignment.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.dkatalis.parkinglot.assignment.dao.IParkingLevelDataManager;
import com.dkatalis.parkinglot.assignment.dao.IParkingLotDataManagement;
import com.dkatalis.parkinglot.assignment.model.Vehicle;
import com.dkatalis.parkinglot.assignment.strategy.NearestFirstParkingStrategy;
import com.dkatalis.parkinglot.assignment.strategy.ParkingStrategy;

public class IParkingLotDataManagementImpl<T extends Vehicle> implements IParkingLotDataManagement<T> {

	private Map<Integer, IParkingLevelDataManager<T>> levelParkingMap;

	@SuppressWarnings("rawtypes")
	private static IParkingLotDataManagementImpl instance = null;

	@SuppressWarnings("unchecked")
	public static <T extends Vehicle> IParkingLotDataManagementImpl<T> getInstance(List<Integer> parkingLevels,
			List<Integer> capacityList, List<ParkingStrategy> parkingStrategies) {
		// Make sure the each of the lists are of equal size
		if (instance == null) {
			synchronized (IParkingLotDataManagementImpl.class) {
				if (instance == null) {
					instance = new IParkingLotDataManagementImpl<T>(parkingLevels, capacityList, parkingStrategies);
				}
			}
		}
		return instance;
	}

	private IParkingLotDataManagementImpl(List<Integer> parkingLevels, List<Integer> capacityList,
			List<ParkingStrategy> parkingStrategies) {
		if (levelParkingMap == null)
			levelParkingMap = new HashMap<>();
		for (int i = 0; i < parkingLevels.size(); i++) {
			levelParkingMap.put(parkingLevels.get(i), IParkingLevelDataManagerImpl.getInstance(parkingLevels.get(i),
					capacityList.get(i), new NearestFirstParkingStrategy()));

		}
	}

	@Override
	public int parkCar(int level, T vehicle) {
		return levelParkingMap.get(level).parkCar(vehicle);
	}

	@Override
	public boolean leaveCar(int level, int slotNumber) {
		return levelParkingMap.get(level).leaveCar(slotNumber);
	}

	@Override
	public List<String> getStatus(int level) {
		return levelParkingMap.get(level).getStatus();
	}

	public int getAvailableSlotsCount(int level) {
		return levelParkingMap.get(level).getAvailableSlotsCount();
	}

	@Override
	public List<String> getRegNumberForColor(int level, String color) {
		return levelParkingMap.get(level).getRegNumberForColor(color);
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(int level, String color) {
		return levelParkingMap.get(level).getSlotNumbersFromColor(color);
	}

	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) {
		return levelParkingMap.get(level).getSlotNoFromRegistrationNo(registrationNo);
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public void doCleanup() {
		for (IParkingLevelDataManager<T> levelDataManager : levelParkingMap.values()) {
			levelDataManager.doCleanUp();
		}
		levelParkingMap = null;
		instance = null;
	}

}
