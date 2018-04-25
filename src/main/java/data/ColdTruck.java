package main.java.data;

public class ColdTruck extends Truck {
	double temperature;

	public ColdTruck(double cost, int capacity, Stock cargo, double temperature) {
		super(cost, capacity, cargo);
		this.temperature = temperature;
		capacity = 800;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	
	

}
