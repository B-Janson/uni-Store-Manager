package main.java.delivery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.controller.Utilities;
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
	
	public double getTotalCost() {
		double totalCost = 0;
		
		for (Truck truck : normalTrucks) {
			totalCost += truck.getCost();
		}
		
		return totalCost;
	}
	
	public Stock getOrder() {
		return order;
	}
	
	public void addItem() {
		
	}
	
	private void organiseTrucks() {
		@SuppressWarnings("unused")
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
		
		for (int i = 0; i < normalItems.size(); i++) {
			Truck truck = new OrdinaryTruck();
			normalTrucks.add(truck);
		}
		
		for (Item item : normalItems) {
			for (Truck truck : normalTrucks) {
				if (truck.addItem(item)) {
					break;
				}
			}
		}
		
		
	}
	
	public ArrayList<Truck> getTrucks() {
		ArrayList<Truck> trucks = new ArrayList<>();
		trucks.addAll(coldTrucks);
		trucks.addAll(normalTrucks);
		return trucks;
	}
	
	public void saveToFile(String fileName) throws IOException {
		String toWrite = "";
		
		for (Truck truck : normalTrucks) {
			toWrite += truck.toString() + "\n";
		}
		
		for (Truck truck : coldTrucks) {
			toWrite += truck.toString();
		}
		
		Utilities.writeCSV(fileName, toWrite);
	}

}
