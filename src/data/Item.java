package data;

public class Item {
	String name;
	double cost;
	double price;
	int reorderPoint;
	int reorderAmount;
	double temperature;
	
	
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount, double temperature) {
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temperature = temperature;
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


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	

}
