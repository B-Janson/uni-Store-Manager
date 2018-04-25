package main.java.delivery;

import java.util.ArrayList;

/**
 * @author Brandon Janson
 */
public class Manifest {
	
	private ArrayList<Truck> trucks;

	public Manifest() {
		trucks = new ArrayList<Truck>();
	}
	
	public Manifest(Truck[] trucks) {
		this.trucks = new ArrayList<Truck>();
		for (Truck truck : trucks) {
			this.trucks.add(truck);
		}
	}
	
	public ArrayList<Truck> getTrucks() {
		return trucks;
	}
	
	public void addTruck(Truck truck) {
		trucks.add(truck);
	}
	
	public double getTotalCost() {
		double totalCost = 0;
		
		for (Truck truck : trucks) {
			totalCost += truck.getCost();
		}
		
		return totalCost;
	}

}
