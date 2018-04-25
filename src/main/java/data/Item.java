package main.java.data;

/**
 * Data class to hold information about Items
 * 
 * @author Brandon Janson
 *
 */
public class Item {
	String name;
	double cost;
	double price;
	int reorderPoint;
	int reorderAmount;
	int currAmount;

	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount) {
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.currAmount = 0;
	}

	/**
	 * 
	 * @return <code>true</code> if this item requires a re-order, else
	 *         <code>false</code>
	 */
	public boolean requiresOrder() {
		return currAmount < reorderPoint;
	}

	public String printProperties() {
		return String.format("%S, %S, %S, %S, %S, %S", name, Double.toString(cost), Double.toString(price),
				Integer.toString(reorderPoint), Integer.toString(reorderAmount), Integer.toString(currAmount));
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

	public void setCurrentAmount(int currAmount) {
		this.currAmount = currAmount;
	}

}
