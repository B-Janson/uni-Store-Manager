package main.java.controller;

import java.io.IOException;

import main.java.delivery.Manifest;
import main.java.delivery.Truck;
import main.java.exceptions.CSVException;
import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;

/**
 * Store class used to define Store object, and its parameters and behaviour.
 * Only one instance of a Store can be constructed.
 * 
 * @author Chris Martin
 */
public class Store {
	/**
	 * private constructor to prevent instantiation
	 */
	private Store() {
	}

	/**
	 * Resets Store properties
	 * 
	 * @throws IOException
	 * @throws CSVException
	 */
	public void reset() throws IOException, CSVException {
		capital = 100000; // starting capital
		name = "SuperMart";
		inventory = new Stock();
	}

	/**
	 * Internal class to enable singleton
	 */
	private static class StoreHolder {
		private final static Store INSTANCE = new Store();
	}

	/**
	 * 
	 * @return instance of Store
	 */
	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}

	private double capital;
	private Stock inventory;
	private String name;

	/**
	 * @return the capital
	 */
	public double getCapital() {
		return capital;
	}

	/**
	 * @param capital
	 *            the capital to set
	 */
	public void setCapital(double capital) {
		this.capital = capital;
	}

	/**
	 * @return the inventory
	 */
	public Stock getInventory() {
		return inventory;
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
	 * Takes a filename as an input and reads the file line-by-line, subtracting
	 * quantity from the inventory and adding the sales to the capital.
	 * 
	 * @param filename
	 *            sales log to be input
	 * @throws IOException
	 *             throws if unable to find filename
	 * @throws StockException
	 *             throws if resulting inventory has negative value
	 * @throws CSVException
	 */
	public void doSale(String filename) throws IOException, StockException, CSVException {
		String[] sale = Utilities.readCSV(filename, 2, -1);

		for (String line : sale) {
			String[] details = line.split(",");
			String itemName = details[0];
			int numSold = Integer.parseInt(details[1]);

			Item soldItem = inventory.get(itemName);
			int newAmount = soldItem.getCurrAmount() - numSold;
			soldItem.setCurrAmount(newAmount);

			capital += numSold * soldItem.getPrice();
		}
	}

	/**
	 * Creates a new manifest and adds items which require to be ordered. Then
	 * writes manifest to a .csv file and adjusts the capital according to the cost.
	 * 
	 * @throws IOException
	 *             throws if unable to find filename
	 * @throws StockException
	 *             throws if resulting inventory has a negative value
	 */
	public void generateOrder() throws IOException, StockException {
		Manifest order = new Manifest();

		for (Item item : Store.getInstance().getInventory().getItems()) {
			if (item.requiresOrder()) {
				order.addItem(item);
			}
		}
		order.saveToFile("manifest.csv");

		for (Truck truck : order.getTrucks()) {
			inventory.adjustBy(truck.getCargo(), true);
		}
		capital -= order.getTotalCost();
	}

	/**
	 * Creates a new stock of all item types with a quantity of 0, this is then
	 * added to the current inventory. This method is only called upon when the
	 * store is reset.
	 * 
	 * @throws IOException
	 * @throws CSVException
	 */
	public void loadItems(String filename) throws IOException, CSVException {
		inventory = new Stock();

		String[] itemProperties = Utilities.readCSV(filename, 5, 6);
		for (String line : itemProperties) {
			String[] properties = line.split(",");
			Item item;
			if (properties.length == 5) {
				item = new Item(properties[0], Double.parseDouble(properties[1]), Double.parseDouble(properties[2]),
						Integer.parseInt(properties[3]), Integer.parseInt(properties[4]));
			} else {
				item = new ColdItem(properties[0], Double.parseDouble(properties[1]), Double.parseDouble(properties[2]),
						Integer.parseInt(properties[3]), Integer.parseInt(properties[4]),
						Double.parseDouble(properties[5]));
			}

			inventory.add(item);
		}

	}

}
