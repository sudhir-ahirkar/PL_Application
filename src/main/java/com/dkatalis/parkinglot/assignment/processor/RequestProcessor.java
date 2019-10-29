
package com.dkatalis.parkinglot.assignment.processor;

import com.dkatalis.parkinglot.assignment.exception.ErrorCode;
import com.dkatalis.parkinglot.assignment.exception.ParkingException;
import com.dkatalis.parkinglot.assignment.model.Car;
import com.dkatalis.parkinglot.assignment.service.IParkingLot;

public class RequestProcessor 
{
	private IParkingLot parkingService;
	
	public void setParkingService(IParkingLot parkingService) throws ParkingException
	{
		this.parkingService = parkingService;
	}
	
	public void execute(String input) throws ParkingException
	{
		int level = 1;
		String[] inputs = input.split(" ");
		String key = inputs[0];
		switch (key)
		{
			case IParkingLot.CREATE_PARKING_LOT:
				try
				{
					int capacity = Integer.parseInt(inputs[1]);
					parkingService.createParkingLot(level, capacity);
				}
				catch (NumberFormatException e)
				{
					throw new ParkingException(ErrorCode.INVALID_VALUE.getMessage().replace("{variable}", "capacity"));
				}
				break;
			case IParkingLot.PARK:
				parkingService.park(level, new Car(inputs[1], inputs[2]));
				break;
			case IParkingLot.LEAVE:
				try
				{
					int slotNumber = Integer.parseInt(inputs[1]);
					parkingService.unPark(level, slotNumber);
				}
				catch (NumberFormatException e)
				{
					throw new ParkingException(
							ErrorCode.INVALID_VALUE.getMessage().replace("{variable}", "slot_number"));
				}
				break;
			case IParkingLot.STATUS:
				parkingService.getStatus(level);
				break;
			case IParkingLot.REG_NUMBER_FOR_CARS_WITH_COLOR:
				parkingService.getRegNumberForColor(level, inputs[1]);
				break;
			case IParkingLot.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
				parkingService.getSlotNumbersFromColor(level, inputs[1]);
				break;
			case IParkingLot.SLOTS_NUMBER_FOR_REG_NUMBER:
				parkingService.getSlotNoFromRegistrationNo(level, inputs[1]);
				break;
			default:
				break;
		}
	}
	
	
}
