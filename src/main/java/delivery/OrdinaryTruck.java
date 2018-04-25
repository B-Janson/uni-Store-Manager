package main.java.delivery;

import main.java.stock.Stock;

/**
 * @author Brandon Janson
 */
public class OrdinaryTruck extends Truck {

	public OrdinaryTruck(int capacity, Stock cargo) {
		super(capacity, cargo);
	}
	
	@Override
	public double getCost() {
		int baseCost = 750;
		double pricePerItem = 0.25;
		return baseCost + pricePerItem * getCargo().getQuantity();
	}

}
