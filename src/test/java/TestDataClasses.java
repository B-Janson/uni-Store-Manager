package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import main.java.controller.Utilities;
import main.java.data.Item;

public class TestDataClasses {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCSVRead() {
		try {
			String[] testStrings = Utilities.readCSV("item_properties.csv");
			String[] expected = {"rice,2,3,225,300"};
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
		assertTrue(testItem.requiresOrder());
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
		assertTrue(!testItem.requiresOrder());
	}

}
