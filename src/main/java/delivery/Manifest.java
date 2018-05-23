package main.java.delivery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import main.java.controller.Utilities;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;

/**
 * Manifest object used for storing and handling orders
 * 
 * @author Brandon Janson
 */
public class Manifest {

	/**
	 * List of normal trucks needed to fulfil the order
	 */
	private ArrayList<Truck> normalTrucks;

	/**
	 * List of cold trucks needed to fulfil the order
	 */
	private ArrayList<Truck> coldTrucks;

	/**
	 * Internal stock object to model the order
	 */
	private Stock order;

	/**
	 * Constructor used to set up the internal fields
	 */
	public Manifest() {
		normalTrucks = new ArrayList<>();
		coldTrucks = new ArrayList<>();
		order = new Stock();
	}

	/**
	 * Returns the total cost of the order defined by the sum of the cost of each
	 * truck
	 * 
	 * @return total cost of the order
	 */
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

	/**
	 * Add item to this order
	 * 
	 * @param item
	 *            item to be added to order
	 */
	public void addItem(Item item) {
		Item toAdd;
		if (item instanceof ColdItem) {
			toAdd = new ColdItem(item.getName(), item.getCost(), item.getPrice(), item.getReorderPoint(),
					item.getReorderAmount(), ((ColdItem) item).getTemperature());
		} else {
			toAdd = new Item(item.getName(), item.getCost(), item.getPrice(), item.getReorderPoint(),
					item.getReorderAmount());
		}

		toAdd.setToReorder();
		order.add(toAdd);
	}

	/**
	 * Internal private method use to distribute which items will go on which trucks
	 */
	private void organiseTrucks() {
		ArrayList<Item> normalItems = new ArrayList<>();
		ArrayList<ColdItem> coldItems = new ArrayList<>();

		normalTrucks = new ArrayList<>();
		coldTrucks = new ArrayList<>();

		// turn all items into individual cold and non-cold lists
		for (Item item : order.getItems()) {
			if (item instanceof ColdItem) {
				coldItems.add((ColdItem) item);
			} else {
				normalItems.add(item);
			}
		}

		// This method sorts the coldItems list by temperature in ascending order
		Collections.sort(coldItems);

		// array to keep track of whether item at index i has been put onto a truck
		boolean[] itemAdded = new boolean[coldItems.size()];

		for (int i = 0; i < coldItems.size(); i++) {
			// if current item has already been added, skip it
			if (itemAdded[i]) {
				continue;
			}
			
			ColdItem firstItem = coldItems.get(i);
			int firstItemIndex = i;
			ColdItem secondItem = null;
			int secondItemIndex = -1;

			
			boolean sortedTruck = false;
			int diff = 0;

			while (!sortedTruck) {
				for (int j = i + 1; j < coldItems.size(); j++) {
					secondItem = coldItems.get(j);
					if (ColdTruck.CAPACITY - firstItem.getReorderAmount() - secondItem.getReorderAmount() == diff
							&& !itemAdded[j]) {
						sortedTruck = true;
						secondItemIndex = j;
						break;
					}
				}
				if (diff > 400) {
					sortedTruck = true;
				}
				diff += 25;
			}

			ColdTruck newTruck = new ColdTruck(firstItem.getTemperature());
			newTruck.addItem(firstItem);
			itemAdded[firstItemIndex] = true;
			
			if (secondItemIndex != -1) {
				newTruck.addItem(secondItem);
				itemAdded[secondItemIndex] = true;
			}
			
			coldTrucks.add(newTruck);
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

	/**
	 * Returns all of the trucks needed to fulfill this order
	 * 
	 * @return list of all trucks in manifest
	 */
	public ArrayList<Truck> getTrucks() {
		ArrayList<Truck> trucks = new ArrayList<>();
		trucks.addAll(coldTrucks);
		trucks.addAll(normalTrucks);
		return trucks;
	}

	/**
	 * Saves the current state of the manifest to file. This method also organises
	 * the trucks as it is the first thing that should be called after the order has
	 * been completed.
	 * 
	 * @param fileName
	 *            name of the file to save the manifest to
	 * @throws IOException
	 *             if can't write to specified file
	 */
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
