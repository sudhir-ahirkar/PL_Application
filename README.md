# ParkingLot

Design a Parking Lot using Java.

Solution : 

This is a Parking lot system designed using object oriented principles in Java.

The objects in my design are the actual ParkingLot, Levels, Slots and Vehicles

ParkingLot - A parking lot is made up of 'n'  number of levels and 'm' number of slots per level.

Levels - Each level is an independent entity with a floor number, its lanes and the spots within it. The number of lanes are designed based on the number of spots. 10 Spots make one lane

Vehicles - Object with color,registration number name. A vehicle has the attributes of registration number and color.

I have considered Levels and Spots as entities that are independent so that any level can be added with a desired number of spots later.Each time a vehicle comes in or goes out, a list of vehicles for the particular company is updated.Also the available spots are updated in the particular level.

Methods:- 
 
createParkingLot - This operation create parking lot by providing slot capacity for particular level
park - This operation inserts a vehicle accordingly, also takes care of what vehicle color it is.
unPark - This operation remove a vehicle of particular slot of particular level
Leave Operation  - This operations exits a particular vehicle in a level 'm'.
ColorParked  - This operation allows the user to view the list of vehicles parked for a particular color.
getStatus - This operation display information about all vehicles belongs to particular level
getRegNumberForColor - This operation display RegNumbers of vehicles by providing color and particular level
getSlotNumbersFromColor - This operation display Slots where vehicles are parked by providing color and particular level

Main Program :

I have taken a small example with 6 slots per level and the number of levels are hard coded as n =1 
 (That means, a total of 6 vehicles can be parked. For the seven vehicle, message display on console saying parking is FULL)

Note: There are two ways to run the application

1)  Add command line arguments like "src/main/resources/parking_lot_file_inputs.txt" then application take inputs from file
    which available in application and create parking lot for inputs of file.
                               
                               OR
2) Provide the input as mentioned in the console after run the application 


    
