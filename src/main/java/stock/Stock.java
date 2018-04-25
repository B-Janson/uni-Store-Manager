package main.java.stock;

import java.util.HashMap;
import java.util.Map;

public class Stock {
	
	private StockType type;
	private Map<String, Item> itemList;
	
	public Stock (StockType type) {
		this.type = type;
		itemList = new HashMap<String, Item>();
	}
	
	public void add(Item item) {
		itemList.put(item.getName(), item);
	}
	
	public void remove(Item item) {
		itemList.remove(item.getName());
	}
	
	public int size() {
		return itemList.size();
	}
	
	public StockType getStockType() {
		return type;
	}
	
	public void setStockType(StockType stockType) {
		this.type = stockType;
	}
	
	public Map<String, Item> getItemList() {
		return itemList;
	}
	
	public void adjustBy(Stock otherStock, boolean add) {
		for (Item item : otherStock.getItemList().values()) {
			if (add) {
				itemList.get(item.getName()).adjustAmount(item.getCurrentAmount());
			} else {
				itemList.get(item.getName()).adjustAmount(-item.getCurrentAmount());
			}
		}
	}

}
