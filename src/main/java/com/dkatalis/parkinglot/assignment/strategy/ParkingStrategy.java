/**
 * 
 */
package com.dkatalis.parkinglot.assignment.strategy;


public interface ParkingStrategy
{
	public void add(int i);
	
	public int getSlot();
	
	public void removeSlot(int slot);
}
