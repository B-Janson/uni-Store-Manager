package main.java.delivery;

import main.java.stock.Stock;

public abstract class Truck {
	
	double cost;
	int capacity;
	Stock cargo;

	public Truck(double cost, int capacity, Stock cargo) {
		this.cost = cost;
		this.capacity = capacity;
		this.cargo = cargo;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Stock getCargo() {
		return cargo;
	}

	public void setCargo(Stock cargo) {
		this.cargo = cargo;
	}

}
