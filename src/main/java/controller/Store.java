package main.java.controller;

import main.java.data.Stock;

/**
 * @author Chris
 *
 */
public class Store {
	private Store() {
	}

	double capital;
	Stock inventory;
	String name;

	/*
	 * 
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

	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}

}
