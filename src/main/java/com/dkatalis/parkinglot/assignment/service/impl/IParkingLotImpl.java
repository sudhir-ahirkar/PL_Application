package com.dkatalis.parkinglot.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.dkatalis.parkinglot.assignment.dao.IParkingLotDataManagement;
import com.dkatalis.parkinglot.assignment.dao.impl.IParkingLotDataManagementImpl;
import com.dkatalis.parkinglot.assignment.exception.ErrorCode;
import com.dkatalis.parkinglot.assignment.exception.ParkingException;
import com.dkatalis.parkinglot.assignment.model.Vehicle;
import com.dkatalis.parkinglot.assignment.service.IParkingLot;
import com.dkatalis.parkinglot.assignment.strategy.NearestFirstParkingStrategy;
import com.dkatalis.parkinglot.assignment.strategy.ParkingStrategy;

public class IParkingLotImpl implements IParkingLot {

	private IParkingLotDataManagement<Vehicle> parkingLotDataManagement = null;

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void createParkingLot(int level, int capacity) throws ParkingException {
		if (parkingLotDataManagement != null)
			throw new ParkingException(ErrorCode.PARKING_ALREADY_EXIST.getMessage());
		List<Integer> parkingLevels = new ArrayList<>();
		List<Integer> capacityList = new ArrayList<>();
		List<ParkingStrategy> parkingStrategies = new ArrayList<>();
		parkingLevels.add(level);
		capacityList.add(capacity);
		parkingStrategies.add(new NearestFirstParkingStrategy());
		this.parkingLotDataManagement = IParkingLotDataManagementImpl.getInstance(parkingLevels, capacityList,
				parkingStrategies);
		System.out.println("Created parking lot with " + capacity + " slots");

	}

	public Optional<Integer> park(int level, Vehicle vehicle) throws ParkingException {
		Optional<Integer> value = Optional.empty();
		lock.writeLock().lock();
		validateParkingLot();
		try
		{
			value = Optional.of(parkingLotDataManagement.parkCar(level, vehicle));
			if (value.get() == IParkingLot.NOT_AVAILABLE)
				System.out.println("Sorry, parking lot is full");
			else if (value.get() == IParkingLot.VEHICLE_ALREADY_EXIST)
				System.out.println("Sorry, vehicle is already parked.");
			else
				System.out.println("Allocated slot number: " + value.get());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.writeLock().unlock();
		}
		return value;
	}

	@Override
	public void unPark(int level, int slotNumber) throws ParkingException
	{
		lock.writeLock().lock();
		validateParkingLot();
		try
		{
			
			if (parkingLotDataManagement.leaveCar(level, slotNumber))
				System.out.println("Slot number " + slotNumber + " is free");
			else
				System.out.println("Slot number is Empty Already.");
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.INVALID_VALUE.getMessage().replace("{variable}", "slot_number"), e);
		}
		finally
		{
			lock.writeLock().unlock();
		}
	}
	
	@Override
	public void getStatus(int level) throws ParkingException
	{
		lock.readLock().lock();
		validateParkingLot();
		try
		{
			System.out.println("Slot No.\tRegistration No.\tColor");
			List<String> statusList = parkingLotDataManagement.getStatus(level);
			if (statusList.size() == 0)
				System.out.println("Sorry, parking lot is empty.");
			else
			{
				for (String statusSting : statusList)
				{
					System.out.println(statusSting);
				}
			}
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.readLock().unlock();
		}
	}
	
	public Optional<Integer> getAvailableSlotsCount(int level) throws ParkingException
	{
		lock.readLock().lock();
		Optional<Integer> value = Optional.empty();
		validateParkingLot();
		try
		{
			value = Optional.of(parkingLotDataManagement.getAvailableSlotsCount(level));
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.readLock().unlock();
		}
		return value;
	}
	
	@Override
	public void getRegNumberForColor(int level, String color) throws ParkingException
	{
		lock.readLock().lock();
		validateParkingLot();
		try
		{
			List<String> registrationList = parkingLotDataManagement.getRegNumberForColor(level, color);
			if (registrationList.size() == 0)
				System.out.println("Not Found");
			else
				System.out.println(String.join(",", registrationList));
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.readLock().unlock();
		}
	}
	
	@Override
	public void getSlotNumbersFromColor(int level, String color) throws ParkingException
	{
		lock.readLock().lock();
		validateParkingLot();
		try
		{
			List<Integer> slotList = parkingLotDataManagement.getSlotNumbersFromColor(level, color);
			if (slotList.size() == 0)
				System.out.println("Not Found");
			StringJoiner joiner = new StringJoiner(",");
			for (Integer slot : slotList)
			{
				joiner.add(slot + "");
			}
			System.out.println(joiner.toString());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.readLock().unlock();
		}
	}
	
	@Override
	public int getSlotNoFromRegistrationNo(int level, String registrationNo) throws ParkingException
	{
		int value = -1;
		lock.readLock().lock();
		validateParkingLot();
		try
		{
			value = parkingLotDataManagement.getSlotNoFromRegistrationNo(level, registrationNo);
			System.out.println(value != -1 ? value : "Not Found");
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		finally
		{
			lock.readLock().unlock();
		}
		return value;
	}
	
	@Override
	public void doCleanup()
	{
		if (parkingLotDataManagement != null)
			parkingLotDataManagement.doCleanup();
	}
	
	/**
	 * @throws ParkingException
	 */
	private void validateParkingLot() throws ParkingException
	{
		if (parkingLotDataManagement == null)
		{
			throw new ParkingException(ErrorCode.PARKING_NOT_EXIST_ERROR.getMessage());
		}
	}
}
