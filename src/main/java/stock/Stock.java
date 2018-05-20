package main.java.stock;

import java.util.Collection;
import java.util.HashMap;

import main.java.exceptions.StockException;

/**
 * @author Brandon Janson
 */
public class Stock {

	private HashMap<String, Item> itemList;

	public Stock() {
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

	public HashMap<String, Item> getItemList() {
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
	
	public int getItemAmount(String name) {
		return itemList.get(name).getCurrentAmount();
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Stock otherStock = (Stock) obj;
		
		if (this.getQuantity() != otherStock.getQuantity() || this.size() != otherStock.size()) {
			return false;
		}
		
		for (Item item : itemList.values()) {
			if (!otherStock.contains(item)) {
				return false;
			}
			
			if (otherStock.getItemAmount(item.getName()) != getItemAmount(item.getName())) {
				return false;
			}
		}
		
		return true;
	}
	
}
