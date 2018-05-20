package main.java.delivery;

/**
 * Data class to store all needed information about the non-cold trucks. This
 * extends the abstract Truck class.
 * 
 * @author Brandon Janson
 */
public class OrdinaryTruck extends Truck {

	/**
	 * Specified capacity of Ordinary Trucks
	 */
	public static final int CAPACITY = 1000;

	/**
	 * Sets the capacity of the truck
	 */
	public OrdinaryTruck() {
		super(CAPACITY);
	}

	/**
	 * Overrides Truck's getCost() method, based on specified formula for truck
	 * costs.
	 */
	@Override
	public double getCost() {
		int baseCost = 750;
		double pricePerItem = 0.25;
		return super.getCost() + baseCost + pricePerItem * getCargo().getQuantity();
	}

}
