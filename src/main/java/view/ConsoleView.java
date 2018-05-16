package main.java.view;

import java.util.Scanner;

import main.java.controller.Store;

public class ConsoleView {
	
	Scanner scanner;

	public static void main(String[] args) {
		ConsoleView cView = new ConsoleView();
		cView.run();
	}
	
	public ConsoleView() {
		scanner = new Scanner(System.in);
	}

	public void run() {
		boolean running = true;

		while (running) {
			System.out.print("What would you like to do? ");
			running = handleInput(scanner.nextLine());
		}

		scanner.close();

	}

	public boolean handleInput(String input) {
		Store store = Store.getInstance();
		
		switch (input.toLowerCase()) {
		case "capital":
			System.out.println(store.getCapital());
			break;
		case "name":
			System.out.println(store.getName());
			break;
		case "inventory":
			System.out.println(store.getInventory());
			break;
		case "loaditems":
			loadItems();
			break;
		case "order":
			doOrder();
			break;
		case "sale":
			doSale();
			break;
		case "exit":
		case "quit":
			System.out.println("Exiting.");
			return false;
		default:
			System.out.println("command not found.");
			break;
		}
		return true;
	}

	/**
	 * loads item_properties.csv into the store
	 */
	public void loadItems() {
		Store store = Store.getInstance();		
		store.loadItems();
		System.out.println("Successfully set inventory to " + store.getInventory());
		
//		try {
//			String[] itemProperties = Utilities.readCSV("item_properties.csv");
//			for (String line : itemProperties) {
//				String[] properties = line.split(",");
//				Item item = new Item(properties[0], Double.parseDouble(properties[1]),
//						Double.parseDouble(properties[2]), Integer.parseInt(properties[3]),
//						Integer.parseInt(properties[4]));
//				inventory.add(item);
//			}
//		} catch (IOException e) {
//			System.err.println("You messed up");
//			e.printStackTrace();
//		}

		
	}
	
	public void doOrder() {
		Store store = Store.getInstance();
		store.generateOrder();
		System.out.println("Successfully ordered new stock: " + store.getInventory());
	}
	
	public void doSale() {
		Store store = Store.getInstance();
		
		System.out.print("Which sale would you like to perform? [0, 1, 2, 3, 4] ");
		try {
			int choice = Integer.parseInt(scanner.nextLine());
			if (choice < 0 || choice > 4) {
				System.err.println("Entered non-existant sale");
				return;
			}
			String fileName = String.format("sales_log_%d.csv", choice);
			store.doSale(fileName);
		} catch (Exception e) {
			System.err.println("Entered a non-integer value");
			e.printStackTrace();
		}
	}

}
