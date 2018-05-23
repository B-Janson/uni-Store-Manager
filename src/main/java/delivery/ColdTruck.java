package main.java.delivery;

/**
 * ColdTruck class used to store differences from abstract Truck class. These
 * include the capacity, temperature and the method of finding the cost.
 * 
 * @author Chris Martin
 */
public class ColdTruck extends Truck {
	/*
	 * Specified capacity for ColdTruck
	 */
	public static final int CAPACITY = 800;
	
	/*
	 * Variable only applicable to ColdTruck. Stores temperature at which this
	 * specific truck will be kept.
	 */
	private double temperature;

	/**
	 * Constructor setting capacity and including temperature.
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
	
	/*
	 * Uses getCost method from Truck class and overrides to provide accurate
	 * calculation of cost of ColdTruck.
	 */
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
