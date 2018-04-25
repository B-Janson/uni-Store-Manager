package test.java;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.controller.Utilities;
import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;

public class TestItems {
	
	private Random random;

	private Item rice;
	private Item mushroom;

	private int riceIndex = 0;
	private int mushroomIndex = 8;
	
	private static String[] csvLines;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			csvLines = Utilities.readCSV("item_properties.csv");
			String[] expected = { "rice,2,3,225,300" };
			assertEquals("CSV read failed", expected[0], csvLines[0]);
		} catch (IOException e) {
			fail("IO Exception");
		}
	}

	@Before
	public void setUp() throws Exception {
		random = new Random();

		String[] riceProperties = csvLines[riceIndex].split(",");
		String name = riceProperties[0];
		double cost = Double.parseDouble(riceProperties[1]);
		double price = Double.parseDouble(riceProperties[1]);
		int reorderPoint = Integer.parseInt(riceProperties[3]);
		int reorderAmount = Integer.parseInt(riceProperties[4]);
		int initialAmount = 0;
		rice = new Item(name, cost, price, reorderPoint, reorderAmount);
		assertEquals("Making item from csv failed", rice.printProperties(),
				name + ", " + cost + ", " + price + ", " + reorderPoint + ", " + reorderAmount + ", " + initialAmount);

		String[] mushroomProperties = csvLines[mushroomIndex].split(",");
		name = mushroomProperties[0];
		cost = Double.parseDouble(mushroomProperties[1]);
		price = Double.parseDouble(mushroomProperties[1]);
		reorderPoint = Integer.parseInt(mushroomProperties[3]);
		reorderAmount = Integer.parseInt(mushroomProperties[4]);
		double temperature = Double.parseDouble(mushroomProperties[5]);
		mushroom = new ColdItem(name, cost, price, reorderPoint, reorderAmount, temperature);
		assertEquals("Making item from csv failed", mushroom.printProperties(), name + ", " + cost + ", " + price + ", "
				+ reorderPoint + ", " + reorderAmount + ", " + initialAmount + ", " + temperature);
	}

	@Test
	public void testItemRequiresReorder() throws StockException {
		rice.setCurrentAmount(50);
		assertTrue("requiresOrder not working correctly", rice.requiresOrder());
	}

	@Test
	public void testItemNoReorder() throws StockException {
		rice.setCurrentAmount(rice.getReorderAmount() + 50);
		assertTrue("requiresOrder not working correctly", !rice.requiresOrder());
	}

	@Test
	public void testItemPrint() throws StockException {
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
	public void testColdItemPrint() throws StockException {
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
	
	@Test(expected = StockException.class)
	public void testSetAmount() throws StockException {
		rice.setCurrentAmount(-1 * random.nextInt(100));
	}
	
	@Test
	public void testPositiveAdjustAmount() throws StockException {
		rice.adjustAmount(random.nextInt(100));
	}
	
	@Test(expected = StockException.class)
	public void testNegativeAdjustAmount() throws StockException {
		rice.adjustAmount(-1 * random.nextInt(100));
	}

}
