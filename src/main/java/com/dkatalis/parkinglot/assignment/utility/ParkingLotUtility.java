package com.dkatalis.parkinglot.assignment.utility;

import java.util.HashMap;
import java.util.Map;

import com.dkatalis.parkinglot.assignment.service.IParkingLot;

public class ParkingLotUtility {

	private static volatile Map<String, Integer> commandsParameterMap = new HashMap<String, Integer>();

	static {
		commandsParameterMap.put(IParkingLot.CREATE_PARKING_LOT, 1);
		commandsParameterMap.put(IParkingLot.PARK, 2);
		commandsParameterMap.put(IParkingLot.LEAVE, 1);
		commandsParameterMap.put(IParkingLot.STATUS, 0);
		commandsParameterMap.put(IParkingLot.REG_NUMBER_FOR_CARS_WITH_COLOR, 1);
		commandsParameterMap.put(IParkingLot.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, 1);
		commandsParameterMap.put(IParkingLot.SLOTS_NUMBER_FOR_REG_NUMBER, 1);
	}

	/**
	 * @return the commandsParameterMap
	 */
	public static Map<String, Integer> getCommandsParameterMap() {
		return commandsParameterMap;
	}

	/**
	 * @param commandsParameterMap the commandsParameterMap to set
	 */
	public static void addCommand(String command, int parameterCount) {
		commandsParameterMap.put(command, parameterCount);
	}

	public static boolean validate(String inputString) {
		// Split the input string to validate command and input value
		boolean valid = true;
		try {
			String[] inputs = inputString.split(" ");
			int params = getCommandsParameterMap().get(inputs[0]);
			switch (inputs.length) {
			case 1:
				if (params != 0) // e.g status -> inputs = 1
					valid = false;
				break;
			case 2:
				if (params != 1) // create_parking_lot 6 -> inputs = 2
					valid = false;
				break;
			case 3:
				if (params != 2) // park KA-01-P-333 White -> inputs = 3
					valid = false;
				break;
			default:
				valid = false;
			}
		} catch (Exception e) {
			valid = false;
		}
		return valid;
	}

//	public static VehicleColor get(String color) {
//        
//		if(color!=null && !color.isEmpty())
//			color=color.toUpperCase();
//			
//		return VehicleColor.color;
//	}
}
