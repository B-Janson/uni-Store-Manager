package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;

/**
 * @author Brandon Janson
 */
public class TestItems {

	private static final double PRECISION = 0.001;

	private Random random;
	private Item normalTest;
	private ColdItem coldTest;

	@Before
	public void setUp() throws Exception {
		random = new Random();
		normalTest = MockItem.getRandomNormalItem();
		coldTest = MockItem.getRandomColdItem();
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testItemRequiresReorder() throws StockException {
		normalTest.setCurrAmount(0);
		assertTrue("requiresOrder not working correctly", normalTest.requiresOrder());
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testItemNoReorder() throws StockException {
		normalTest.setCurrAmount(normalTest.getReorderAmount() + 50);
		assertTrue("requiresOrder not working correctly", !normalTest.requiresOrder());
	}
	
	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testItemHasReorderAmount() throws StockException {
		normalTest.setCurrAmount(normalTest.getReorderAmount());
		assertTrue("requiresOrder not working correctly", !normalTest.requiresOrder());
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test(expected = StockException.class)
	public void testSetNegativeAmount() throws StockException {
		normalTest.setCurrAmount(-1 * random.nextInt(100));
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testSetPositiveAmount() throws StockException {
		int randomAmount = random.nextInt(100);
		normalTest.setCurrAmount(randomAmount);
		assertEquals("setCurrentAmount not working correctly", randomAmount, normalTest.getCurrAmount());
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test
	public void testPositiveAdjustAmount() throws StockException {
		int randomAmount = random.nextInt(100);
		normalTest.adjustAmount(randomAmount);
		assertEquals("adjustAmount not working correctly", randomAmount, normalTest.getCurrAmount());
	}

	/**
	 * 
	 * @throws StockException
	 * @author Brandon Janson
	 */
	@Test(expected = StockException.class)
	public void testNegativeAdjustAmount() throws StockException {
		normalTest.adjustAmount(-1 * random.nextInt(100));
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testName() {
		String newName = "apple";
		normalTest.setName(newName);
		assertEquals("set/getName not working correctly", newName, normalTest.getName());
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testCost() {
		double testCost = random.nextDouble() * 100;
		normalTest.setCost(testCost);
		assertEquals("set/getCost not working correctly", testCost, normalTest.getCost(), PRECISION);
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testPrice() {
		double testPrice = random.nextDouble() * 100;
		normalTest.setPrice(testPrice);
		assertEquals("set/getPrice not working correctly", testPrice, normalTest.getPrice(), PRECISION);
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testReorderPoint() {
		int testPoint = random.nextInt(100);
		normalTest.setReorderPoint(testPoint);
		assertEquals("set/getReorderPoint not working correctly", testPoint, normalTest.getReorderPoint(), PRECISION);
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testReorderAmount() {
		int testAmount = random.nextInt(100);
		normalTest.setReorderAmount(testAmount);
		assertEquals("set/getReorderAmount not working correctly", testAmount, normalTest.getReorderAmount(),
				PRECISION);
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testSetTemperature() {
		double temperature = random.nextInt(100) - 50;
		coldTest.setTemperature(temperature);
		assertEquals("set/getTemperature not working correctly", temperature, coldTest.getTemperature(), PRECISION);
	}

}
