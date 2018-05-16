package main.java.controller;

import java.io.IOException;

import main.java.delivery.Manifest;
import main.java.delivery.Truck;
import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;

/**
 * @author Chris Martin
 */
public class Store {
	private Store() {
		reset();
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
	 * 
	 * @return current capital of Store
	 */
	public double getCapital() {
		return capital;
	}

	/**
	 * 
	 * @param capital specified capital for Store
	 */
	public void setCapital(double capital) {
		this.capital = capital;
	}

	/**
	 * 
	 * @return current inventory as a Stock object of Store
	 */
	public Stock getInventory() {
		return inventory;
	}

	/**
	 * 
	 * @param inventory specified inventory for Store
	 */
	public void setInventory(Stock inventory) {
		this.inventory = inventory;
	}

	/**
	 * 
	 * @return name of Store
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void updateInventory(Manifest trucks) throws StockException {
		for (Truck truck : trucks.getTrucks()) {
			System.out.println(truck.getCargo());
			inventory.adjustBy(truck.getCargo(), false);
		}
	}

	public void updateCapital(Manifest trucks) {
		capital = capital - trucks.getTotalCost();
	}

	public void doSale(String filename) throws IOException, StockException {
		String[] sale = Utilities.readCSV(filename);
		
		for (String line : sale) {
			String[] details = line.split(",");
			
			Stock soldItems = new Stock(StockType.SalesLogs);
			Item name = inventory.getItemList().get(details[0]);
			int newAmount = inventory.getItemAmount(details[0]) - Integer.parseInt(details[1]);
			name.setCurrentAmount(newAmount);
			soldItems.add(name);
			inventory.adjustBy(soldItems, false);
		}
	}

	public void generateOrder() throws IOException, StockException {
		Manifest order = new Manifest();
		
		for (Item item : Store.getInstance().getInventory().getItems()) {
			if (item.requiresOrder()) {
				order.addItem(item);
			}
		}
		order.saveToFile("manifest.csv");
		//TO-DO add functionality to read csv file
		
		for (Truck truck : order.getTrucks()) {
			inventory.adjustBy(truck.getCargo(), true);
		}
		capital -= order.getTotalCost();
		
	}

	public void loadItems() {
		inventory = new Stock(StockType.StoreInventory);
		
		try {
			String[] itemProperties = Utilities.readCSV("item_properties.csv");
			for (String line : itemProperties) {
				String[] properties = line.split(",");
				Item item;
				if (properties.length == 5) {
					item = new Item(properties[0], Double.parseDouble(properties[1]),
							Double.parseDouble(properties[2]), Integer.parseInt(properties[3]),
							Integer.parseInt(properties[4]));
				} else {
					item = new ColdItem(properties[0], Double.parseDouble(properties[1]),
							Double.parseDouble(properties[2]), Integer.parseInt(properties[3]),
							Integer.parseInt(properties[4]), Double.parseDouble(properties[5]));
				}
				
				inventory.add(item);
			}
		} catch (IOException e) {
			System.err.println("You messed up");
			e.printStackTrace();
		}
	}

	public void reset() {
		loadItems();
		capital = 100000;
		name = "SuperMart";
	}

}
