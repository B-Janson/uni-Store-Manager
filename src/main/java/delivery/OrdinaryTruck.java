package main.java.delivery;

import main.java.stock.Stock;

/**
 * @author Brandon Janson
 */
public class OrdinaryTruck extends Truck {
	
	private static final int CAPACITY = 1000;

	public OrdinaryTruck(Stock cargo) {
		super(CAPACITY, cargo);
	}
	
	@Override
	public double getCost() {
		int baseCost = 750;
		double pricePerItem = 0.25;
		return baseCost + pricePerItem * getCargo().getQuantity();
	}

}
