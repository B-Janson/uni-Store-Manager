package main.java.controller;

import main.java.delivery.Manifest;
import main.java.delivery.Truck;
import main.java.exceptions.StockException;
import main.java.stock.Stock;

/**
 * @author Chris Martin
 */
public class Store {
	private Store() {
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
	
	public void reset() {
		
	}

}
