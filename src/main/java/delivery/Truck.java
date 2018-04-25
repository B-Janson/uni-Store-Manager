package main.java.delivery;

import main.java.stock.Stock;

/**
 * 
 * @author Chris Martin
 *
 */
public abstract class Truck {

	private int capacity;
	private Stock cargo;

	public Truck(int capacity, Stock cargo) {
		this.capacity = capacity;
		this.cargo = cargo;
	}

	public abstract double getCost();

	public int getCapacity() {
		return capacity;
	}

	public Stock getCargo() {
		return cargo;
	}

	public void setCargo(Stock cargo) {
		this.cargo = cargo;
	}

}
