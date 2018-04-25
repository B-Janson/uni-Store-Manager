package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import main.java.controller.Utilities;
import main.java.stock.ColdItem;
import main.java.stock.Item;

public class TestItems {

	Item rice;
	Item mushroom;

	int riceIndex = 0;
	int mushroomIndex = 8;

	@Before
	public void setUp() throws Exception {
		String[] csvLines = {};

		try {
			csvLines = Utilities.readCSV("item_properties.csv");
			String[] expected = { "rice,2,3,225,300" };
			assertEquals("CSV read failed", expected[0], csvLines[0]);
		} catch (IOException e) {
			fail("IO Exception");
		}

		String[] riceProperties = csvLines[riceIndex].split(",");
		String name = riceProperties[0];
		double cost = Double.parseDouble(riceProperties[1]);
		double price = Double.parseDouble(riceProperties[1]);
		int reorderPoint = Integer.parseInt(riceProperties[3]);
		int reorderAmount = Integer.parseInt(riceProperties[4]);
		rice = new Item(name, cost, price, reorderPoint, reorderAmount);
		assertEquals("Making item from csv failed", rice.printProperties(),
				name + cost + price + reorderPoint + reorderAmount);

		String[] mushroomProperties = csvLines[mushroomIndex].split(",");
		name = mushroomProperties[0];
		cost = Double.parseDouble(mushroomProperties[1]);
		price = Double.parseDouble(mushroomProperties[1]);
		reorderPoint = Integer.parseInt(mushroomProperties[3]);
		reorderAmount = Integer.parseInt(mushroomProperties[4]);
		double temperature = Double.parseDouble(mushroomProperties[5]);
		mushroom = new ColdItem(name, cost, price, reorderPoint, reorderAmount, temperature);
		assertEquals("Making item from csv failed", mushroom.printProperties(),
				name + cost + price + reorderPoint + reorderAmount);
	}

	@Test
	public void testItemClassRequiresReorder() {
		String name = "rice";
		int cost = 2;
		int price = 3;
		int reorderPoint = 225;
		int reorderAmount = 300;
		Item testItem = new Item(name, cost, price, reorderPoint, reorderAmount);
		testItem.setCurrentAmount(50);
		assertTrue("requiresOrder not working correctly", testItem.requiresOrder());
	}

	@Test
	public void testItemClassNoReorder() {
		String name = "beans";
		int cost = 4;
		int price = 6;
		int reorderPoint = 450;
		int reorderAmount = 525;
		Item testItem = new Item(name, cost, price, reorderPoint, reorderAmount);
		testItem.setCurrentAmount(reorderPoint + 50);
		assertTrue("requiresOrder not working correctly", !testItem.requiresOrder());
	}

	@Test
	public void testItemPrint() {
		String name = "bread";
		double cost = 1.5;
		double price = 2.8;
		int reorderPoint = 80;
		int reorderAmount = 140;
		Item testItem = new Item(name, cost, price, reorderPoint, reorderAmount);
		testItem.setCurrentAmount(107);
		assertEquals("printProperties does not return string correctly.", testItem.printProperties(),
				"bread, 1.5, 2.8, 80, 140, 107");
	}

	@Test
	public void testColdItemPrint() {
		String name = "milk";
		double cost = 2.0;
		double price = 3.5;
		int reorderPoint = 350;
		int reorderAmount = 500;
		double temperature = 3.0;
		ColdItem testItem = new ColdItem(name, cost, price, reorderPoint, reorderAmount, temperature);
		testItem.setCurrentAmount(623);
		assertEquals("printProperties does not return string correctly.", testItem.printProperties(),
				"milk, 2.0, 3.5, 350, 500, 623, 3.0");
	}

}
