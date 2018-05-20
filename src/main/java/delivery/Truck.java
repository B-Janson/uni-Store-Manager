package main.java.delivery;

import main.java.stock.Item;
import main.java.stock.Stock;

/**
 * @author Chris Martin
 */
public abstract class Truck {

	private int capacity;
	private Stock cargo;

	/**
	 * both truck types have capcity and cargo and are thus included in abstract
	 * constructor
	 * 
	 * @param capacity
	 *            capacity can be one of two values depending on which truck
	 */
	public Truck(int capacity) {
		this.capacity = capacity;
		this.cargo = new Stock();
	}

	/**
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
	 * capacity is one of two values depending on which truck
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
	 * 
	 * @param item
	 *            Item object to be added to cargo
	 * @return
	 */
	public boolean addItem(Item item) {
		if (cargo.getQuantity() + item.getReorderAmount() > capacity) {
			return false;
		}
		cargo.add(item);
		return true;
	}

	/**
	 * converts name and current amount of item in cargo to string
	 */
	public String toString() {
		String out = String.format(">%s\n", getClass().getSimpleName());

		for (Item item : cargo.getItems()) {
			out += String.format("%s, %d\n", item.getName(), item.getReorderAmount());
		}

		return out;
	}

}
