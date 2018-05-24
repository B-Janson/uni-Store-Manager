package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;

/**
 * @author Chris Martin
 */
public class TestStock {
	private Stock originalStock;
	private Item normalItem;
	private ColdItem coldItem;
	private Random random;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		originalStock = new Stock();

		normalItem = MockItem.getRandomNormalItem();
		coldItem = MockItem.getRandomColdItem();

		random = new Random();
	}

	@Test
	public void testAdjustByPositive() throws StockException {
		Stock newStock = new Stock();

		Item newItem = MockItem.getRandomNormalItem();
		ColdItem newColdItem = MockItem.getRandomColdItem();

		while (newItem.getName().equals(normalItem.getName())) {
			newItem = MockItem.getRandomNormalItem();
		}

		while (newColdItem.getName().equals(coldItem.getName())) {
			newColdItem = MockItem.getRandomColdItem();
		}

		newItem.setCurrentAmount(random.nextInt(100));
		newColdItem.setCurrentAmount(random.nextInt(100));

		originalStock.add(normalItem);
		originalStock.add(coldItem);

		newStock.add(newItem);
		newStock.add(newColdItem);

		originalStock.adjustBy(newStock, true);

		assertTrue(originalStock.contains(newItem));
		assertTrue(originalStock.contains(newColdItem));
	}

	@Test
	public void testAdjustByNegative() throws StockException {
		Stock newStock = new Stock();

		normalItem.setToReorder();
		coldItem.setToReorder();

		// Copy original item
		Item newItem = new Item(normalItem.getName(), normalItem.getCost(), normalItem.getCost(),
				normalItem.getReorderPoint(), normalItem.getReorderAmount());
		newItem.setCurrentAmount(random.nextInt(50));

		ColdItem newColdItem = new ColdItem(coldItem.getName(), coldItem.getCost(), coldItem.getCost(),
				coldItem.getReorderPoint(), coldItem.getReorderAmount(), coldItem.getTemperature());
		newColdItem.setCurrentAmount(random.nextInt(50));

		originalStock.add(normalItem);
		originalStock.add(coldItem);

		newStock.add(newItem);
		newStock.add(newColdItem);

		originalStock.adjustBy(newStock, false);

		assertTrue(originalStock.contains(newItem));
		assertTrue(originalStock.contains(newColdItem));
	}

	@Test(expected = StockException.class)
	public void testAdjustByNegativeError() throws StockException {
		Stock newStock = new Stock();

		normalItem.setToReorder();
		coldItem.setToReorder();

		// Copy original item
		Item newItem = new Item(normalItem.getName(), normalItem.getCost(), normalItem.getCost(),
				normalItem.getReorderPoint(), normalItem.getReorderAmount());
		newItem.setCurrentAmount(1000);

		ColdItem newColdItem = new ColdItem(coldItem.getName(), coldItem.getCost(), coldItem.getCost(),
				coldItem.getReorderPoint(), coldItem.getReorderAmount(), coldItem.getTemperature());
		newColdItem.setCurrentAmount(1000);

		originalStock.add(normalItem);
		originalStock.add(coldItem);

		newStock.add(newItem);
		newStock.add(newColdItem);

		originalStock.adjustBy(newStock, false);

		assertTrue(originalStock.contains(newItem));
		assertTrue(originalStock.contains(newColdItem));
	}

	@Test
	public void testAdd() throws StockException {
		Item banana = new Item("banana", 1, 1.5, 10, 15);
		banana.setCurrentAmount(15);

		assertFalse("Stock.add does not funtion correctly", originalStock.contains(banana));

		originalStock.add(banana);

		assertTrue("Stock.add does not function correctly", originalStock.contains(banana));
	}

	@Test
	public void testRemove() throws StockException {
		Item newItem = MockItem.getRandomNormalItem();
		originalStock.add(newItem);
		assertTrue(originalStock.contains(newItem));

		originalStock.remove(newItem);
		assertFalse("Stock.remove does not funtion correctly", originalStock.contains(newItem));
	}

	@Test(expected = StockException.class)
	public void testRemoveException() throws StockException {
		Item newItem = MockItem.getRandomNormalItem();
		originalStock.remove(newItem);

		assertFalse("Stock.remove does not funtion correctly", originalStock.contains(newItem));
	}

	@Test
	public void testSize() throws StockException {
		assertEquals("Stock.size does not function correctly", 0, originalStock.size());

		Item banana = new Item("banana", 1, 1.5, 10, 15);
		banana.setCurrentAmount(15);
		originalStock.add(banana);

		assertEquals(1, originalStock.size());
	}

	@Test
	public void testQuantity() throws StockException {
		assertEquals(0, originalStock.getQuantity());

		int normalAmount = random.nextInt(1000);
		normalItem.setCurrentAmount(normalAmount);

		int coldAmount = random.nextInt(1000);
		coldItem.setCurrentAmount(coldAmount);

		originalStock.add(normalItem);
		originalStock.add(coldItem);

		assertEquals(normalAmount + coldAmount, originalStock.getQuantity());

	}

	@Test
	public void testStockEquality() throws StockException {
		Stock newStock = new Stock();
		assertEquals(newStock, originalStock);

		int normalAmount = random.nextInt(1000);
		normalItem.setCurrentAmount(normalAmount);

		int coldAmount = random.nextInt(1000);
		coldItem.setCurrentAmount(coldAmount);

		originalStock.add(normalItem);
		originalStock.add(coldItem);
		
		newStock.add(normalItem);
		newStock.add(coldItem);

		assertEquals(newStock, originalStock);
	}

}
