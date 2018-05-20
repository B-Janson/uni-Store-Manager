package main.java.delivery;


/**
 * @author Brandon Janson
 */
public class OrdinaryTruck extends Truck {
	
	private static final int CAPACITY = 1000;

	public OrdinaryTruck() {
		super(CAPACITY);
	}
	
	@Override
	public double getCost() {
		int baseCost = 750;
		double pricePerItem = 0.25;
		return super.getCost() + baseCost + pricePerItem * getCargo().getQuantity();
	}

}
