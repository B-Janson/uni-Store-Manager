package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import main.java.data.Item;

/**
 * @author Brandon Janson
 *
 */
public class TestStore {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method to ensure that store has capacity of 100000 when first loaded
	 */
	@Test
	public void testInitialStoreCapital() {
		Store store = Store.getInstance();
		store.init();
		int expectedCapital = 100000;
		assertTrue("store should have initial capital of " + expectedCapital, store.getCapital() == expectedCapital);
	}

	/**
	 * Test method to ensure that all items are initialised to 0 when the store is
	 * first created
	 */
	@Test
	public void testInitialStoreInventory() {
		Store store = Store.getInstance();
		store.init();
		int expectedQuantity = 0;
		List<Item> listOfItems = store.getInventory();
		boolean allCorrect = true;
		for (Item item : listOfItems) {
			if (item.getCurrentAmount() != expectedQuantity) {
				allCorrect = false;
				break;
			}
		}
		assertTrue("all items should have an initial amount of " + expectedQuantity, allCorrect);
	}

}
