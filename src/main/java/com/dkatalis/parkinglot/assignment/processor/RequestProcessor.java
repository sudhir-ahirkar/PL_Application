
package com.dkatalis.parkinglot.assignment.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.dkatalis.parkinglot.assignment.exception.ErrorCode;
import com.dkatalis.parkinglot.assignment.exception.ParkingException;
import com.dkatalis.parkinglot.assignment.model.Car;
import com.dkatalis.parkinglot.assignment.service.IParkingLot;
import com.dkatalis.parkinglot.assignment.utility.ParkingLotUtility;

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
	
	public void processInput(String[] args) throws IOException {
		BufferedReader bufferReader = null;
		String input = null;
		try
		{

			System.out.println("=============Welcome to PARKING LOT application =============");
			printUsage();
			switch (args.length)
			{
				case 0: // Interactive: command-line input/output
				{
					System.out.println("Please Enter 'exit' to end Execution");
					System.out.println("Input:");
					while (true)
					{
						try
						{
							bufferReader = new BufferedReader(new InputStreamReader(System.in));
							input = bufferReader.readLine().trim();
							if (input.equalsIgnoreCase("exit"))
							{
								break;
							}
							else
							{
								if (ParkingLotUtility.validate(input))
								{
									try
									{
										this.execute(input.trim());
									}
									catch (Exception e)
									{
										System.out.println(e.getMessage());
									}
								}
								else
								{
									printUsage();
								}
							}
						}
						catch (Exception e)
						{
							throw new ParkingException(ErrorCode.INVALID_REQUEST.getMessage(), e);
						}
					}
					break;
				}
				case 1:// File input/output
				{
					File inputFile = new File(args[0]);
					try
					{
						bufferReader = new BufferedReader(new FileReader(inputFile));
						int lineNo = 1;
						while ((input = bufferReader.readLine()) != null)
						{
							input = input.trim();
							if (ParkingLotUtility.validate(input))
							{
								try
								{
									this.execute(input);
								}
								catch (Exception e)
								{
									System.out.println(e.getMessage());
								}
							}
							else
								System.out.println("Incorrect Command Found at line: " + lineNo + " ,Input: " + input);
							lineNo++;
						}
					}
					catch (Exception e)
					{
						throw new ParkingException(ErrorCode.INVALID_FILE.getMessage(), e);
					}
					break;
				}
				default:
					System.out.println("Invalid input. Usage Style: java -jar <jar_file_path> <input_file_path>");
			}
		}
		catch (ParkingException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				if (bufferReader != null)
					bufferReader.close();
			}
			catch (IOException e)
			{
			}
		}

	}
	
	
	private static void printUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append(
				"----Please Enter one of the below commands OR Add command line arguments like \"src/main/resources/parking_lot_file_inputs.txt\" tor run application --")
				.append("\n");
		
		buffer = buffer.append(
				"--------------Please Enter one of the below commands  -----------------------")
				.append("\n");
		buffer = buffer.append("A) For creating parking lot of size n               ---> create_parking_lot {capacity}")
				.append("\n");
		buffer = buffer
				.append("B) To park a car                                    ---> park <<car_number>> {car_clour}")
				.append("\n");
		buffer = buffer.append("C) Remove(Unpark) car from parking                  ---> leave {slot_number}")
				.append("\n");
		buffer = buffer.append("D) Print status of parking slot                     ---> status").append("\n");
		buffer = buffer.append(
				"E) Get cars registration no for the given car color ---> registration_numbers_for_cars_with_color {car_color}")
				.append("\n");
		buffer = buffer.append(
				"F) Get slot numbers for the given car color         ---> slot_numbers_for_cars_with_color {car_color}")
				.append("\n");
		buffer = buffer.append(
				"G) Get slot number for the given car number         ---> slot_number_for_registration_number {car_number}")
				.append("\n");
		System.out.println(buffer.toString());
	}
}
