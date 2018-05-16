package main.java.stock;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.exceptions.StockException;

/**
 * @author Brandon Janson
 */
public class Stock {

	private StockType type;
	private Map<String, Item> itemList;

	public Stock(StockType type) {
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

	public void adjustBy(Stock otherStock, boolean add) throws StockException {
		for (Item item : otherStock.getItemList().values()) {
			if (add) {
				itemList.get(item.getName()).adjustAmount(item.getCurrentAmount());
			} else {
				itemList.get(item.getName()).adjustAmount(-item.getCurrentAmount());
			}
		}
	}

	public int getQuantity() {
		int quantity = 0;
		
		for (Item item : itemList.values()) {
			quantity += item.getCurrentAmount();
		}
		
		return quantity;
	}

	public boolean contains(Item item) {
		return itemList.containsKey(item.getName());
	}
	
	public Collection<Item> getItems() {
		return itemList.values();
	}
	
	@Override
	public String toString() {
		String retVal = "";
		
		for (Item item : itemList.values()) {
			retVal += String.format("%s:%d,", item.getName(), item.getCurrentAmount());
		}
		
		return String.format("{%s}", retVal);
	}

}
