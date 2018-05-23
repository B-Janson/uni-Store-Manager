package main.java.delivery;

import main.java.stock.Item;
import main.java.stock.Stock;

/**
 * Abstract class Truck stores properties applicable to both cold and non-cold
 * truck.
 * 
 * @author Chris Martin
 */
public abstract class Truck {

	private int capacity;
	private Stock cargo;

	/**
	 * Both truck types have capacity and cargo and are thus included in abstract
	 * constructor.
	 * 
	 * @param capacity
	 *            capacity can be one of two values depending on which truck
	 */
	public Truck(int capacity) {
		this.capacity = capacity;
		this.cargo = new Stock();
	}

	/**
	 * Calculates cost of cargo stored within truck.
	 * 
	 * @return cost of cost of truck calculated in subclass
	 */
	public double getCost() {
		double itemCosts = 0;
		for (Item item : cargo.getItems()) {
			itemCosts += item.getCost() * item.getCurrentAmount();
		}
		return itemCosts;
	}

	/**
	 * Capacity is an aspect of both truck types, but each has a different value.
	 * 
	 * @return capacity of truck
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return the cargo
	 */
	public Stock getCargo() {
		return cargo;
	}

	/**
	 * Returns a boolean whether or not a specific item has been added to cargo.
	 * 
	 * @param item
	 *            Item object to be added to cargo
	 * @return boolean
	 */
	public boolean addItem(Item item) {
		if (cargo.getQuantity() + item.getReorderAmount() > capacity) {
			return false;
		}
		cargo.add(item);
		return true;
	}

	/**
	 * Converts name and current amount of item in cargo to string.
	 */
	public String toString() {
		String out = String.format(">%s\n", getClass().getSimpleName());

		for (Item item : cargo.getItems()) {
			out += String.format("%s, %d\n", item.getName(), item.getReorderAmount());
		}

		return out;
	}

}
