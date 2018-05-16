package main.java.delivery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.controller.Utilities;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;

/**
 * @author Brandon Janson
 */
public class Manifest {
	
	// TODO this class should take in items and divvy them up onto trucks
	
	private ArrayList<Truck> normalTrucks;
	private ArrayList<Truck> coldTrucks;
	private Stock order;

	public Manifest() {
		normalTrucks = new ArrayList<>();
		coldTrucks = new ArrayList<>();
		order = new Stock(StockType.StockOrders);
	}
	
	public double getTotalCost() {
		double totalCost = 0;
		
		for (Truck truck : normalTrucks) {
			totalCost += truck.getCost();
		}
		
		for (Truck truck : coldTrucks) {
			totalCost += truck.getCost();
		}
		
		return totalCost;
	}
	
	public void addItem(Item item) {
		Item toAdd;
		if (item instanceof ColdItem) {
			toAdd = new ColdItem(item.getName(), item.getCost(), item.getPrice(), item.getReorderPoint(), item.getReorderAmount(), ((ColdItem) item).getTemperature());
		} else {
			toAdd = new Item(item.getName(), item.getCost(), item.getPrice(), item.getReorderPoint(), item.getReorderAmount());
		}
		
		toAdd.setToReorder();
		order.add(toAdd);
	}
	
	private void organiseTrucks() {
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
		
		for (Item item : coldItems) {
			int i = -1;
			do {
				i++;
				if (coldTrucks.size() == i) {
					coldTrucks.add(new ColdTruck(null, 10));
				}
				
			} while (!coldTrucks.get(i).addItem(item));
		}
		
		for (Item item : normalItems) {
			int i = -1;
			do {
				i++;
				if (normalTrucks.size() == i) {
					normalTrucks.add(new OrdinaryTruck());
				}
				
			} while (!normalTrucks.get(i).addItem(item));
		}
		
//		for (int i = 0; i < normalItems.size(); i++) {
//			Truck truck = new OrdinaryTruck();
//			normalTrucks.add(truck);
//		}
//		
//		for (Item item : normalItems) {
//			for (Truck truck : normalTrucks) {
//				if (truck.addItem(item)) {
//					break;
//				}
//			}
//		}
		
		
	}
	
	public ArrayList<Truck> getTrucks() {
		ArrayList<Truck> trucks = new ArrayList<>();
		trucks.addAll(coldTrucks);
		trucks.addAll(normalTrucks);
		
		for (Truck truck : trucks) {
			if(truck.getCargo() == null) {
				trucks.remove(truck);
			}
		}
		return trucks;
	}
	
	public void saveToFile(String fileName) throws IOException {
		organiseTrucks();
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
