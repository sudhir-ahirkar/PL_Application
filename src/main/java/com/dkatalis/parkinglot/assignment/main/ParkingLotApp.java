package com.dkatalis.parkinglot.assignment.main;

import java.io.IOException;

import com.dkatalis.parkinglot.assignment.exception.ParkingException;
import com.dkatalis.parkinglot.assignment.processor.RequestProcessor;
import com.dkatalis.parkinglot.assignment.service.impl.IParkingLotImpl;

public class ParkingLotApp {

	public static void main(String[] args) throws ParkingException, IOException {
		RequestProcessor processor = new RequestProcessor();
		processor.setParkingService(new IParkingLotImpl());
		processor.processInput(args);
	}

}
