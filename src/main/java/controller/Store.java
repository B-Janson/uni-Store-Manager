package main.java.controller;

import com.sun.org.apache.xml.internal.security.signature.Manifest;

import main.java.data.Stock;

/**
 * @author Chris
 *
 */
public class Store {
	private Store() {
	}

	private double capital;
	private Stock inventory;
	private String name;

	/**
	 * Internal class to enable singleton
	 */
	private static class StoreHolder {
		private final static Store INSTANCE = new Store();
	}

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

	public void updateInventory(Manifest trucks) {
		for (Truck truck : trucks.getTrucks()) {
			inventory.adjustBy(truck.getCargo(), false);
		}
	}
	
	public void updateCapital (Manifest trucks) {
		capital = capital - trucks.getCost();
	}

	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}

}
