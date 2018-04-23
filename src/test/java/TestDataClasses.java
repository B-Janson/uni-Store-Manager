package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.Item;

public class TestDataClasses {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCSVRead() {
		fail("not implemented");
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
