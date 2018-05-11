package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.controller.Store;
import main.java.delivery.Manifest;
import main.java.delivery.OrdinaryTruck;
import main.java.exceptions.StockException;
import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;
import test.java.MockItem.MockItemType;

public class TestStore {

	private static final double PRECISION = 0.001;

	private Stock inventory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Store initialStore = Store.getInstance();
		Stock initialInventory = new Stock(StockType.StoreInventory);
		double expectedInitialCapital = 0;

		assertEquals("initial capital should be 0", expectedInitialCapital, initialStore.getCapital(),
				PRECISION);
//		assertEquals("initial inventory should be an empty stock object", initialInventory,
//				initialStore.getInventory());
	}

	@Before
	public void setUp() throws Exception {
		Store.getInstance().reset();
		inventory = new Stock(StockType.StoreInventory);
	}

	@Test
	public void testSingletonProperty() {
		Store store1 = Store.getInstance();
		Store store2 = Store.getInstance();
		assertEquals("Singleton property incorrect", store1, store2);
	}

	@Test
	public void testStoreSetCapital() {
		Store store = Store.getInstance();
		double capital = 2000;
		store.setCapital(capital);
		assertEquals("get/setCapital failed", capital, store.getCapital(), PRECISION);
	}

	@Test
	public void testStoreSetInventory() {
		Store store = Store.getInstance();
		inventory.add(new MockItem(MockItemType.RICE).getItem());
		store.setInventory(inventory);
		assertEquals("set store inventory failed", inventory, store.getInventory());
	}

	@Test
	public void testStoreName() {
		Store store = Store.getInstance();
		String name = "SuperMart";
		store.setName(name);
		assertEquals("set store name failed", name, store.getName());
		
		String newName = "NotSuperMart";
		store.setName(newName);
		assertEquals("set store name failed", newName, store.getName());
	}

	@Test
	public void testStoreUpdateInventoryEmptyManifest() throws StockException {
		Store store = Store.getInstance();
		double expectedCapital = store.getCapital();
		Stock expectedInventory = store.getInventory();
		String name = store.getName();
		
		Stock order = new Stock(StockType.StockOrders);

		Manifest manifest = new Manifest(order);
		store.updateInventory(manifest);

		assertEquals("empty manifest shouldn't change store's capital", expectedCapital, store.getCapital(),
				PRECISION);
		assertEquals("empty manifest shouldn't change store's inventory", expectedInventory, store.getInventory());
		assertEquals("empty manifest shouldn't change store's name", name, store.getName()); 
	}

	@Test
	public void testStoreUpdateInventory() throws StockException {		
		Store store = Store.getInstance();
		double expectedCapital = store.getCapital();
		String name = store.getName();
		
		Stock order = new Stock(StockType.StockOrders);
		
		MockItem rice = MockItem.ITEM_RICE;
		rice.reorder();
		order.add(rice.getItem());
		
		MockItem mushroom = MockItem.ITEM_MUSHROOM;
		mushroom.reorder();
		order.add(mushroom.getItem());
		
		Manifest manifest = new Manifest(order);		
		expectedCapital -= manifest.getTotalCost();
		Stock expectedInventory = manifest.getOrder();
		
		store.updateInventory(manifest);
		assertEquals("capital should decrease", expectedCapital, store.getCapital(), PRECISION);
		assertEquals("inventory should change according to manifest", expectedInventory, store.getInventory());
		assertEquals("name should not change", name, store.getName());
	}

}
