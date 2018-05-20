package main.java.delivery;

/**
 * @author Chris Martin
 */
public class ColdTruck extends Truck {
	private static final int CAPACITY = 800;

	private double temperature;

	/**
	 * 
	 * @param cargo
	 *            Stock object containing items on truck
	 * @param temperature
	 *            double storing temperature of truck
	 */
	public ColdTruck(double temperature) {
		super(CAPACITY);
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

	@Override
	public double getCost() {
		int baseCost = 900;
		int modifer = 200;
		double tempRate = 0.7;
		int tempScale = 5;
		double cost = baseCost + modifer * Math.pow(tempRate, temperature / tempScale);
		return super.getCost() + cost;
	}

}
