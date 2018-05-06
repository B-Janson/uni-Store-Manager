package main.java.delivery;

import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;

/**
 * @author Chris Martin
 */
public abstract class Truck {

	private int capacity;
	private Stock cargo;

	public Truck(int capacity) {
		this.capacity = capacity;
		this.cargo = new Stock(StockType.TruckCargo);
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
	
	public boolean addItem(Item item) {
		cargo.add(item);
		return false;
	}

}
