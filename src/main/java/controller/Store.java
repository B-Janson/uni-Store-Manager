package main.java.controller;

import java.io.IOException;

import main.java.delivery.Manifest;
import main.java.delivery.Truck;
import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;

/**
 * @author Chris Martin
 */
public class Store {
	/**
	 * private constructor to prevent instantiation
	 */
	private Store() {
		reset();
	}

	/**
	 * resets Store properties
	 */
	public void reset() {
		loadItems();
		capital = 100000;
		name = "SuperMart";
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
	 * reads in sales log, adjusts Store capital and inventory
	 * 
	 * @param filename
	 *            sales log to be input
	 * @throws IOException
	 *             throws if unable to find filename
	 * @throws StockException
	 *             throws if resulting inventory has negative value
	 */
	public void doSale(String filename) throws IOException, StockException {
		String[] sale = Utilities.readCSV(filename);

		for (String line : sale) {
			String[] details = line.split(",");
			String itemName = details[0];
			int numSold = Integer.parseInt(details[1]);

			Item soldItem = inventory.get(itemName);
			int newAmount = soldItem.getCurrentAmount() - numSold;
			soldItem.setCurrentAmount(newAmount);

			capital += numSold * soldItem.getPrice();
		}
	}

	/**
	 * generates manifest trucks with stock of items needed to be reordered
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
	 * loads Store with initial quantities only called upon when Store is reset
	 */
	public void loadItems() {
		inventory = new Stock();

		try {
			String[] itemProperties = Utilities.readCSV("item_properties.csv");
			for (String line : itemProperties) {
				String[] properties = line.split(",");
				Item item;
				if (properties.length == 5) {
					item = new Item(properties[0], Double.parseDouble(properties[1]), Double.parseDouble(properties[2]),
							Integer.parseInt(properties[3]), Integer.parseInt(properties[4]));
				} else {
					item = new ColdItem(properties[0], Double.parseDouble(properties[1]),
							Double.parseDouble(properties[2]), Integer.parseInt(properties[3]),
							Integer.parseInt(properties[4]), Double.parseDouble(properties[5]));
				}

				inventory.add(item);
			}
		} catch (IOException e) {
			System.err.println("item_properties.csv not found in directory");
			e.printStackTrace();
		}
	}

}
