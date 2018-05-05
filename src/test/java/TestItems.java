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

/**
 * @author Brandon Janson
 */
public class TestItems {
	
	private static final double PRECISION = 0.001;
	
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
		double price = Double.parseDouble(riceProperties[2]);
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
	
	@Test(expected = StockException.class)
	public void testSetNegativeAmount() throws StockException {
		rice.setCurrentAmount(-1 * random.nextInt(100));
	}
	
	@Test
	public void testSetPositiveAmount() throws StockException {
		int randomAmount = random.nextInt(100);
		rice.setCurrentAmount(randomAmount);
		assertEquals("setCurrentAmount not working correctly", randomAmount, rice.getCurrentAmount());
	}
	
	@Test
	public void testPositiveAdjustAmount() throws StockException {
		int randomAmount = random.nextInt(100);
		rice.adjustAmount(randomAmount);
		assertEquals("adjustAmount not working correctly", randomAmount, rice.getCurrentAmount());
	}
	
	@Test(expected = StockException.class)
	public void testNegativeAdjustAmount() throws StockException {
		rice.adjustAmount(-1 * random.nextInt(100));
	}
	
	@Test
	public void testName() {
		assertEquals("original name for rice not set correctly", "rice", rice.getName());
		
		String newName = "apple";
		rice.setName(newName);
		assertEquals("set/getName not working correctly", newName, rice.getName());
	}
	
	@Test
	public void testCost() {
		double actualCost = 2;
		assertEquals("original cost for rice not set correctly", actualCost, rice.getCost(), PRECISION);

		double testCost = random.nextDouble() * 100;
		rice.setCost(testCost);
		assertEquals("set/getCost not working correctly", testCost, rice.getCost(), PRECISION);
	}
	
	@Test
	public void testPrice() {
		double actualPrice = 3;
		assertEquals("original cost for rice not set correctly", actualPrice, rice.getPrice(), PRECISION);

		double testPrice = random.nextDouble() * 100;
		rice.setPrice(testPrice);
		assertEquals("set/getPrice not working correctly", testPrice, rice.getPrice(), PRECISION);
	}
	
	@Test
	public void testReorderPoint() {
		int actualPoint = 225;
		assertEquals("original reorder point for rice not set correctly", actualPoint, rice.getReorderPoint());

		int testPoint = random.nextInt(100);
		rice.setReorderPoint(testPoint);
		assertEquals("set/getReorderPoint not working correctly", testPoint, rice.getReorderPoint(), PRECISION);
	}
	
	@Test
	public void testReorderAmount() {
		int actualAmount = 300;
		assertEquals("original reorder amount for rice not set correctly", actualAmount, rice.getReorderAmount());

		int testAmount = random.nextInt(100);
		rice.setReorderAmount(testAmount);
		assertEquals("set/getReorderAmount not working correctly", testAmount, rice.getReorderAmount(), PRECISION);
	}

}
