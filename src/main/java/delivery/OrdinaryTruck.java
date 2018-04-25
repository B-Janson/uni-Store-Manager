package main.java.delivery;

import main.java.stock.Stock;

/**
 * @author Brandon Janson
 */
public class OrdinaryTruck extends Truck {

	public OrdinaryTruck(double cost, int capacity, Stock cargo) {
		super(cost, capacity, cargo);
	}
	
	@Override
	public double getCost() {
//		return 750 + 0.25 * cargo.getNumberOfItems();
		return 0;
	}

}
