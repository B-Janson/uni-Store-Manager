package main.java.delivery;

import main.java.stock.Stock;

/**
 * @author Chris Martin
 */
public class ColdTruck extends Truck {
	private static final int CAPACITY = 800;
	
	private double temperature;

	public ColdTruck(Stock cargo, double temperature) {
		super(CAPACITY);
		this.temperature = temperature;
	}

	public double getTemperature() {
		return temperature;
	}

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
		return cost;
	}
	
}
