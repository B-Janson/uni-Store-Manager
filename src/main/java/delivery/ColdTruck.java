package main.java.delivery;

import main.java.stock.Stock;

/**
 * @author Chris Martin
 */
public class ColdTruck extends Truck {
	private static final int CAPACITY = 800;
	
	private double temperature;

	public ColdTruck(Stock cargo, double temperature) {
		super(CAPACITY, cargo);
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
		return Double.MAX_VALUE;
	}
	
}
