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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(int reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	public int getReorderAmount() {
		return reorderAmount;
	}

	public void setReorderAmount(int reorderAmount) {
		this.reorderAmount = reorderAmount;
	}

	public int getCurrentAmount() {
		return currAmount;
	}

	public void setCurrentAmount(int amount) throws StockException {
		if (amount < 0) {
			throw new StockException();
		}
		this.currAmount = amount;
	}
	
	public void setToReorder() {
		this.currAmount = this.reorderAmount;
	}
	
	@Override
	public String toString() {
		return String.format("%s, cost:%1.2f, price:%1.2f, rePnt:%d, reAmt:%d, curAmt:%d", name, cost, price, reorderPoint, reorderAmount, currAmount);
	}

}
