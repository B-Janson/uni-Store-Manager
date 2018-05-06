package main.java.delivery;

import java.util.ArrayList;
import java.util.HashMap;

import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;

/**
 * @author Brandon Janson
 */
public class Manifest {
	
	// TODO this class should take in items and divvy them up onto trucks
	
	private ArrayList<Truck> normalTrucks;
	private ArrayList<Truck> coldTrucks;
	private Stock order;

	public Manifest(Stock order) {
		normalTrucks = new ArrayList<>();
		coldTrucks = new ArrayList<>();
		this.order = order;
		organiseTrucks();
	}
	
//	public Manifest(Truck[] trucks) {
//		this.trucks = new ArrayList<Truck>();
//		for (Truck truck : trucks) {
//			this.trucks.add(truck);
//		}
//	}
	
//	public ArrayList<Truck> getTrucks() {
////		return trucks;
//		return null;
//	}
	
//	public void addTruck(Truck truck) {
//		trucks.add(truck);
//	}
	
	public double getTotalCost() {
		double totalCost = 0;
		
		for (Truck truck : normalTrucks) {
			totalCost += truck.getCost();
		}
		
		return totalCost;
	}
	
	public void addItem() {
		
	}
	
	private void organiseTrucks() {
		int sizeOfOrder = order.getQuantity();
		HashMap<String, Item> items = order.getItemList();
		
		ArrayList<Item> normalItems = new ArrayList<>();		
		ArrayList<Item> coldItems = new ArrayList<>();
		
		for (Item item : items.values()) {
			if (item instanceof ColdItem) {
				coldItems.add(item);
			} else {
				normalItems.add(item);
			}
		}
		
//		Truck firstTruck = new ColdTruck(null, temperature);
		
//		for (Item item : coldItems) {
//			for (Truck truck : coldTrucks) {
//				
//			}
//		}
		
		Truck secondTruck = new OrdinaryTruck();
		normalTrucks.add(secondTruck);
		
		for (Item item : normalItems) {
			for (Truck truck : normalTrucks) {
				if (!truck.addItem(item)) {
					Truck newTruck = new OrdinaryTruck();
					normalTrucks.add(newTruck);
				}
			}
		}
		
		
	}

}
