package data;

public class ColdItem extends Item {

	public ColdItem(String name, double cost, double price, int reorderPoint, int reorderAmount, double temperature) {
		super(name, cost, price, reorderPoint, reorderAmount);
		this.temperature = temperature;
	}

}
