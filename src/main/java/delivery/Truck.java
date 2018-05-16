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

	/**
	 * both truck types have capcity and cargo and are thus included in abstract
	 * constructor
	 * 
	 * @param capacity
	 *            capacity can be one of two values depending on which truck
	 */
	public Truck(int capacity) {
		this.capacity = capacity;
		this.cargo = new Stock(StockType.TruckCargo);
	}

	/**
	 * 
	 * @return cost of cost of truck calculated in subclass
	 */
	public abstract double getCost();

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
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(Stock cargo) {
		this.cargo = cargo;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 
	 * @param item
	 *            Item object to be added to cargo
	 * @return
	 */
	public boolean addItem(Item item) {
		cargo.add(item);
		return false;
	}

	/**
	 * converts name and current amount of item in cargo to string
	 */
	public String toString() {
		String out = String.format(">%s\n", getClass().getSimpleName());

		for (Item item : cargo.getItemList().values()) {
			out += String.format("%s, %d\n", item.getName(), item.getCurrentAmount());
		}

		return out;
	}

}
