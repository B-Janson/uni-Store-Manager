package main.java.stock;

import java.util.Collection;
import java.util.HashMap;

import main.java.exceptions.StockException;

/**
 * Object used to maintain the state and any other information required about a
 * stock. Stock can be either the store's inventory, a truck's cargo, a
 * manifest's order for example.
 * 
 * @author Brandon Janson
 */
public class Stock {

	/**
	 * Internal representation of a stock is a HashMap to link the names of items to
	 * the items
	 */
	private HashMap<String, Item> itemList;

	/**
	 * Constructor used to set up the internal itemList as an empty HashMap
	 */
	public Stock() {
		itemList = new HashMap<String, Item>();
	}

	/**
	 * Add an item to the stock
	 * 
	 * @param item
	 *            item to add
	 */
	public void add(Item item) {
		itemList.put(item.getName(), item);
	}

	/**
	 * Remove an item from the stock. This method compares by name so these need to
	 * be equal.
	 * 
	 * @param item
	 *            the item object to remove
	 * @throws StockException
	 *             if cannot find given item
	 */
	public void remove(Item item) throws StockException {
		if (itemList.remove(item.getName()) == null) {
			throw new StockException("Item does not exist in this stock");
		}
	}

	/**
	 * Returns the specified item by its name
	 * 
	 * @param itemName
	 *            the name of the item to return
	 * @return the item specified by its name
	 */
	public Item get(String itemName) {
		return itemList.get(itemName);
	}

	/**
	 * Returns the size of the stock. Note: This is the number of item object in the
	 * Map, not the total quantity of items. I.E. it does not accommodate for the
	 * current amount of items inside the map.
	 * 
	 * @return the size of the item list
	 */
	public int size() {
		return itemList.size();
	}

	/**
	 * Method used to adjust one stock object by another stock object. If add is
	 * true, then this stock will be increased by the given stock. If it is false,
	 * then this stock will be decreased by the given stock.
	 * 
	 * @param otherStock
	 *            the stock object to adjust this one by
	 * @param add
	 *            if true, otherStock will be added to this. If false, otherStock
	 *            will be subtracted from this.
	 * @throws StockException
	 *             if any item will be negative as a result of this process
	 */
	public void adjustBy(Stock otherStock, boolean add) throws StockException {
		for (Item item : otherStock.getItems()) {
			if (add) {
				itemList.get(item.getName()).adjustAmount(item.getCurrentAmount());
			} else {
				itemList.get(item.getName()).adjustAmount(-item.getCurrentAmount());
			}
		}
	}

	/**
	 * Returns the quantity of items in the stock. I.E. the sum of all item's
	 * current amounts.
	 * 
	 * @return the quantity of items.
	 */
	public int getQuantity() {
		int quantity = 0;

		for (Item item : itemList.values()) {
			quantity += item.getCurrentAmount();
		}

		return quantity;
	}

	/**
	 * Wrapper method to determine if a given item exists in the stock.
	 * 
	 * @param item
	 *            the item to test for membership
	 * @return true if the item is in this stock, false if not
	 */
	public boolean contains(Item item) {
		return itemList.containsKey(item.getName());
	}

	/**
	 * Wrapper method to return the Items in the stock. Useful to prevent any access
	 * to the internal itemList.
	 * 
	 * @return the collection of items to iterate over
	 */
	public Collection<Item> getItems() {
		return itemList.values();
	}

	/**
	 * Method used to display the contents of the stock object in a human-readable
	 * manner. The format is {name:current_amount, name:current_amount...}
	 */
	@Override
	public String toString() {
		String retVal = "";

		for (Item item : itemList.values()) {
			retVal += String.format("%s:%d,", item.getName(), item.getCurrentAmount());
		}

		return String.format("{%s}", retVal);
	}

	/**
	 * Returns the amount of a given item in the current Stock.
	 * 
	 * @param item
	 *            the item which we need to know the amount of it
	 * @return the amount of the specified item
	 */
	private int getItemAmount(Item item) {
		return itemList.get(item.getName()).getCurrentAmount();
	}

	/**
	 * Overridden method for comparing equality between Stock objects. This was
	 * overridden for testing purposes, but is useful for comparing Stock objects.
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

			if (otherStock.getItemAmount(item) != getItemAmount(item)) {
				return false;
			}
		}

		return true;
	}

}
