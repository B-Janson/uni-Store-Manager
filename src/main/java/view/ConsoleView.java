package main.java.view;

import java.util.Scanner;

import main.java.controller.Store;

public class ConsoleView {

	/**
	 * Scanner object used to read input
	 */
	private Scanner scanner;

	/**
	 * Main entry point into program This class and therefore method is only used to
	 * test functionality and program flow before GUI was created
	 * 
	 * @param args
	 *            input arguments -- not used in this case
	 */
	public static void main(String[] args) {
		ConsoleView cView = new ConsoleView();
		cView.run();
	}

	/**
	 * Constructor for this class, used to prevent having everything static
	 */
	public ConsoleView() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Main method used for this mock class Runs forever until user enters either
	 * 'quit' or 'exit'
	 */
	public void run() {
		boolean running = true;

		while (running) {
			System.out.print("What would you like to do? ");
			running = handleInput(scanner.nextLine());
		}
		scanner.close();

	}

	/**
	 * Helper method for dealing with user input This method takes what the user
	 * input and performs the required methods, as well as printing out any helpful
	 * information
	 * 
	 * @param input
	 *            the input string typed by the user
	 * @return false if the program should exit, true otherwise
	 */
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
	 * Loads item_properties.csv into the store
	 */
	public void loadItems() {
		Store store = Store.getInstance();
		store.loadItems();
		System.out.println("Successfully set inventory to " + store.getInventory());
	}

	/**
	 * Generates an order based on the current state of the store's inventory This
	 * will generate a manifest, save the csv to disk, and then read this csv to
	 * adjust both capital and inventory
	 */
	public void doOrder() {
		Store store = Store.getInstance();
		store.generateOrder();
		System.out.println("Successfully ordered new stock: " + store.getInventory());
	}

	/**
	 * Asks user for which sale log to use and then updates the store's inventory
	 * and capital accordingly
	 */
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
