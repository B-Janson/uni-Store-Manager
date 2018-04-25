package test.java;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;

/**
 * @author Chris
 *
 */
public class TestStock {
	Stock originalStock;
	Item originalMilk;
	Item originalBread;
	Item originalSoap;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		originalStock = new Stock(StockType.StoreInventory);

		originalMilk = new Item("milk", 2, 3, 10, 20);
		originalBread = new Item("bread", 1.5, 2.5, 5, 15);
		originalSoap = new Item("soap", 1, 4.5, 10, 50);

		originalMilk.adjustAmount(15);
		originalBread.adjustAmount(5);
		originalSoap.adjustAmount(18);

		originalStock.add(originalMilk);
		originalStock.add(originalBread);
		originalStock.add(originalSoap);
	}

	@Test
	public void testAdjustBy() {
		Stock newStock = new Stock(StockType.StockOrders);

		Item newMilk = new Item("milk", 2, 3, 10, 20);
		Item newSoap = new Item("soap", 1, 4.5, 10, 50);

		newMilk.adjustAmount(10);
		newSoap.adjustAmount(17);

		originalStock.add(originalMilk);
		originalStock.add(originalBread);
		originalStock.add(originalSoap);

		newStock.add(newMilk);
		newStock.add(newSoap);

		originalStock.adjustBy(newStock, true);

		assertEquals(25, originalMilk.getCurrentAmount());
		assertEquals(5, originalBread.getCurrentAmount());
		assertEquals(35, originalSoap.getCurrentAmount());
	}

	@Test
	public void testAdd() {
		assertTrue("Stock.add does not function correctly", originalStock.contains(originalSoap));

		Item banana = new Item("banana", 1, 1.5, 10, 15);
		banana.adjustAmount(12);

		assertFalse("Stock.add does not funtion correctly", originalStock.contains(banana));

		originalStock.add(banana);

		assertTrue("Stock.add does not function correctly", originalStock.contains(originalBread));
	}

	@Test
	public void testRemove() {
		assertTrue("Stock.remove does not funtion correctly", originalStock.contains(originalBread));

		originalStock.remove(originalBread);
		;

		assertTrue("Stock.remove does not funtion correctly", originalStock.contains(originalMilk));
		assertFalse("Stock.remove does not function correctly", originalStock.contains(originalBread));
	}

	@Test
	public void testSize() {
		assertEquals("Stock.size does not function correctly", 3, originalStock.size());

		Item banana = new Item("banana", 1, 1.5, 10, 15);
		banana.adjustAmount(12);
		originalStock.add(banana);

		assertEquals(4, originalStock.size());
	}

}
