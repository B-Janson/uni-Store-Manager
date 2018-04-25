package main.java.stock;

import java.util.ArrayList;

public class Stock {
	
	private StockType type;
	private ArrayList<Item> itemList;
	
	public Stock (StockType type) {
		this.type = type;
		itemList = new ArrayList<Item>();
	}
	
	public void add(Item item) {
		itemList.add(item);
	}
	
	public boolean remove(Item item) {
		return itemList.remove(item);
	}
	
	public int size() {
		return itemList.size();
	}

}
