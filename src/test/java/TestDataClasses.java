package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import main.java.controller.Utilities;
import main.java.data.ColdItem;
import main.java.data.Item;

public class TestDataClasses {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCSVRead() {
		try {
			String[] testStrings = Utilities.readCSV("item_properties.csv");
			String[] expected = { "rice,2,3,225,300" };
			assertEquals(expected[0], testStrings[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		assertTrue("printProperties does not return string correctly.",
				testItem.printProperties() == "bread, 1.5, 2.8, 80, 140, 107");
	}

	@Test
	public void testColdItemPrint() {
		String name = "milk";
		double cost = 2;
		double price = 3.5;
		int reorderPoint = 350;
		int reorderAmount = 500;
		double temperature = 3;
		ColdItem testItem = new ColdItem(name, cost, price, reorderPoint, reorderAmount, temperature);
		testItem.setCurrentAmount(623);
		assertTrue("printProperties does not return string correctly.",
				testItem.printProperties() == "milk, 2, 3.5, 350, 500, 623, 3");
	}

}
