package test.java;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.controller.Utilities;
import main.java.exceptions.StockException;
import main.java.stock.Item;
import test.java.MockItem.MockItemType;

/**
 * @author Brandon Janson
 */
public class TestItems {
	
	private static final double PRECISION = 0.001;
	
	private Random random;

	private Item rice;
	private Item mushroom;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String[] csvLines = Utilities.readCSV("item_properties.csv");
			String[] expected = { "rice,2,3,225,300" };
			assertEquals("CSV read failed", expected[0], csvLines[0]);
		} catch (IOException e) {
			fail("IO Exception");
		}
	}

	@Before
	public void setUp() throws Exception {
		random = new Random();
		rice = new MockItem(MockItemType.RICE).getItem();
		mushroom = new MockItem(MockItemType.MUSHROOMS).getItem();
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testItemRequiresReorder() throws StockException {
		rice.setCurrentAmount(50);
		assertTrue("requiresOrder not working correctly", rice.requiresOrder());
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testItemNoReorder() throws StockException {
		rice.setCurrentAmount(rice.getReorderAmount() + 50);
		assertTrue("requiresOrder not working correctly", !rice.requiresOrder());
	}
	
	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test(expected = StockException.class)
	public void testSetNegativeAmount() throws StockException {
		rice.setCurrentAmount(-1 * random.nextInt(100));
	}
	
	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testSetPositiveAmount() throws StockException {
		int randomAmount = random.nextInt(100);
		rice.setCurrentAmount(randomAmount);
		assertEquals("setCurrentAmount not working correctly", randomAmount, rice.getCurrentAmount());
	}
	
	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testPositiveAdjustAmount() throws StockException {
		int randomAmount = random.nextInt(100);
		rice.adjustAmount(randomAmount);
		assertEquals("adjustAmount not working correctly", randomAmount, rice.getCurrentAmount());
	}
	
	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test(expected = StockException.class)
	public void testNegativeAdjustAmount() throws StockException {
		rice.adjustAmount(-1 * random.nextInt(100));
	}
	
	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testName() {
		assertEquals("original name for rice not set correctly", "rice", rice.getName());
		
		String newName = "apple";
		rice.setName(newName);
		assertEquals("set/getName not working correctly", newName, rice.getName());
	}
	
	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testCost() {
		double actualCost = 2;
		assertEquals("original cost for rice not set correctly", actualCost, rice.getCost(), PRECISION);

		double testCost = random.nextDouble() * 100;
		rice.setCost(testCost);
		assertEquals("set/getCost not working correctly", testCost, rice.getCost(), PRECISION);
	}
	
	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testPrice() {
		double actualPrice = 3;
		assertEquals("original cost for rice not set correctly", actualPrice, rice.getPrice(), PRECISION);

		double testPrice = random.nextDouble() * 100;
		rice.setPrice(testPrice);
		assertEquals("set/getPrice not working correctly", testPrice, rice.getPrice(), PRECISION);
	}
	
	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testReorderPoint() {
		int actualPoint = 225;
		assertEquals("original reorder point for rice not set correctly", actualPoint, rice.getReorderPoint());

		int testPoint = random.nextInt(100);
		rice.setReorderPoint(testPoint);
		assertEquals("set/getReorderPoint not working correctly", testPoint, rice.getReorderPoint(), PRECISION);
	}
	
	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testReorderAmount() {
		int actualAmount = 300;
		assertEquals("original reorder amount for rice not set correctly", actualAmount, rice.getReorderAmount());

		int testAmount = random.nextInt(100);
		rice.setReorderAmount(testAmount);
		assertEquals("set/getReorderAmount not working correctly", testAmount, rice.getReorderAmount(), PRECISION);
	}

}
