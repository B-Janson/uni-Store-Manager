package main.java.stock;

/**
 * Object to store information about any cold items needed by the program.
 * 
 * @author Brandon Janson
 */
public class ColdItem extends Item {

	/**
	 * The temperature of the item
	 */
	private double temperature;

	/**
	 * Constructor for building a Cold Item object
	 * 
	 * @param name
	 *            name of the item
	 * @param cost
	 *            cost of the item
	 * @param price
	 *            price of the item
	 * @param reorderPoint
	 *            the minimum amount of this item required to prevent a reorder
	 * @param reorderAmount
	 *            the amount that will be reordered
	 * @param temperature
	 *            the required temperature of the item
	 */
	public ColdItem(String name, double cost, double price, int reorderPoint, int reorderAmount, double temperature) {
		super(name, cost, price, reorderPoint, reorderAmount);
		this.temperature = temperature;
	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * toString method to display all of the properties of the item
	 */
	@Override
	public String toString() {
		return super.toString() + String.format(", temp:%1.2f", temperature);
	}

}
