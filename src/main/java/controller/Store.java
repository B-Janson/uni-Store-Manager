package main.java.controller;

import java.io.IOException;

import main.java.delivery.Manifest;
import main.java.delivery.Truck;
import main.java.exceptions.StockException;
import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;

/**
 * @author Chris Martin
 */
public class Store {
	private Store() {
		loadItems();
	}

	/**
	 * Internal class to enable singleton
	 */
	private static class StoreHolder {
		private final static Store INSTANCE = new Store();
	}

	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}

	private double capital;
	private Stock inventory;
	private String name;

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public Stock getInventory() {
		return inventory;
	}

	public void setInventory(Stock inventory) {
		this.inventory = inventory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void updateInventory(Manifest trucks) throws StockException {
		for (Truck truck : trucks.getTrucks()) {
			inventory.adjustBy(truck.getCargo(), false);
		}
	}

	public void updateCapital(Manifest trucks) {
		capital = capital - trucks.getTotalCost();
	}

	public void doSale(String filename) {

	}

	public void generateOrder() {

	}

	public void loadItems() {
		inventory = new Stock(StockType.StoreInventory);
		
		try {
			String[] itemProperties = Utilities.readCSV("item_properties.csv");
			for (String line : itemProperties) {
				String[] properties = line.split(",");
				Item item = new Item(properties[0], Double.parseDouble(properties[1]),
						Double.parseDouble(properties[2]), Integer.parseInt(properties[3]),
						Integer.parseInt(properties[4]));
				inventory.add(item);
			}
		} catch (IOException e) {
			System.err.println("You messed up");
			e.printStackTrace();
		}
	}

	public void reset() {

	}

}
