package main.java.stock;

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

	public String printProperties() {
		return String.format("%S, %S, %S, %S, %S, %S, %S", name, Double.toString(cost), Double.toString(price),
				Integer.toString(reorderPoint), Integer.toString(reorderAmount), Integer.toString(currAmount),
				Double.toString(temperature));
	}

}
