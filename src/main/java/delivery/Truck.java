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

	public abstract double getCost();

	public int getCapacity() {
		return capacity;
	}

	public Stock getCargo() {
		return cargo;
	}

	public void setCargo(Stock cargo) {
		this.cargo = cargo;
	}

}
