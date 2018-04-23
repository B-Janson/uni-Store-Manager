package main.java.data;

public class ColdItem extends Item {

	private double temperature;

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

}
