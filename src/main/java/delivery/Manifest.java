package main.java.delivery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

	public Manifest() {
		normalTrucks = new ArrayList<>();
		coldTrucks = new ArrayList<>();
		order = new Stock();
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
		ArrayList<ColdItem> coldItems = new ArrayList<>();
		
		normalTrucks = new ArrayList<>();
		coldTrucks = new ArrayList<>();
		
		for (Item item : items.values()) {
			if (item instanceof ColdItem) {
				coldItems.add((ColdItem) item);
			} else {
				normalItems.add(item);
			}
		}
		
//		coldItems.sort(Comparator.comparing(ColdItem::getTemperature));
		
		Collections.sort(coldItems);
		
		for (ColdItem item : coldItems) {
			int i = -1;
			do {
				i++;
				if (coldTrucks.size() == i) {
					coldTrucks.add(new ColdTruck(item.getTemperature()));
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
	}
	
	public ArrayList<Truck> getTrucks() {
		ArrayList<Truck> trucks = new ArrayList<>();
		trucks.addAll(coldTrucks);
		trucks.addAll(normalTrucks);
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
