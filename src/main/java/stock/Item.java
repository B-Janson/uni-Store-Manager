package main.java.stock;

import main.java.exceptions.StockException;

/**
 * Data class to hold information about Items
 * 
 * @author Chris Martin
 */
public class Item {
	private String name;
	private double cost;
	private double price;
	private int reorderPoint;
	private int reorderAmount;
	private int currAmount;

	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount) {
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.currAmount = 0;
	}

	/**
	 * Function to determine whether this item requires a re-order This is true if
	 * the current amount is less than the reorder amount
	 * 
	 * @return <code>true</code> if this item requires a re-order, else
	 *         <code>false</code>
	 */
	public boolean requiresOrder() {
		return currAmount < reorderPoint;
	}

	/**
	 * Method used to adjust the current quantity of this particular item
	 * 
	 * @param amount
	 *            amount to adjust by (can be negative of positive)
	 * @throws StockException
	 *             if, as a result of this method, a negative amount of the item is
	 *             remaining
	 */
	public void adjustAmount(int amount) throws StockException {
		if (currAmount + amount < 0) {
			throw new StockException("Cannot have negative amount of items.");
		}
		this.currAmount += amount;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the reorderPoint
	 */
	public int getReorderPoint() {
		return reorderPoint;
	}

	/**
	 * @param reorderPoint
	 *            the reorderPoint to set
	 */
	public void setReorderPoint(int reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	/**
	 * @return the reorderAmount
	 */
	public int getReorderAmount() {
		return reorderAmount;
	}

	/**
	 * @param reorderAmount
	 *            the reorderAmount to set
	 */
	public void setReorderAmount(int reorderAmount) {
		this.reorderAmount = reorderAmount;
	}

	/**
	 * @return the currAmount
	 */
	public int getCurrAmount() {
		return currAmount;
	}

	/**
	 * @param currAmount
	 *            the currAmount to set
	 */
	public void setCurrAmount(int currAmount) {
		this.currAmount = currAmount;
	}

	/**
	 * Sets current amount to reorder amount for generating orders.
	 */
	public void setToReorder() {
		this.currAmount = this.reorderAmount;
	}

	/**
	 * Converts item properties into one String
	 */
	@Override
	public String toString() {
		return String.format("%s, cost:%1.2f, price:%1.2f, rePnt:%d, reAmt:%d, curAmt:%d", name, cost, price,
				reorderPoint, reorderAmount, currAmount);
	}

}
