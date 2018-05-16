package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.controller.Store;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;

public class TestStore {

	private static final double PRECISION = 0.01;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Store initialStore = Store.getInstance();
		Stock initialInventory = new Stock(StockType.StoreInventory);

		initialInventory.add(new Item("rice", 2, 3, 225, 300));
		initialInventory.add(new Item("beans", 4, 6, 450, 525));
		initialInventory.add(new Item("pasta", 3, 4, 125, 250));
		initialInventory.add(new Item("biscuits", 2, 5, 450, 575));
		initialInventory.add(new Item("nuts", 5, 9, 125, 250));
		initialInventory.add(new Item("chips", 2, 4, 125, 200));
		initialInventory.add(new Item("chocolate", 5, 8, 250, 375));
		initialInventory.add(new Item("bread", 2, 3, 125, 200));

		initialInventory.add(new ColdItem("mushrooms", 2, 4, 200, 325, 10));
		initialInventory.add(new ColdItem("tomatoes", 1, 2, 325, 400, 10));
		initialInventory.add(new ColdItem("lettuce", 1, 2, 250, 350, 10));
		initialInventory.add(new ColdItem("grapes", 4, 6, 125, 225, 9));
		initialInventory.add(new ColdItem("asparagus", 2, 4, 175, 275, 8));
		initialInventory.add(new ColdItem("celery", 2, 3, 225, 350, 8));
		initialInventory.add(new ColdItem("chicken", 10, 14, 325, 425, 4));
		initialInventory.add(new ColdItem("beef", 12, 17, 425, 550, 3));
		initialInventory.add(new ColdItem("fish", 13, 16, 375, 475, 2));
		initialInventory.add(new ColdItem("yoghurt", 10, 12, 200, 325, 3));
		initialInventory.add(new ColdItem("milk", 2, 3, 300, 425, 3));
		initialInventory.add(new ColdItem("cheese", 4, 7, 375, 450, 3));
		initialInventory.add(new ColdItem("ice cream", 8, 14, 175, 250, -20));
		initialInventory.add(new ColdItem("ice", 2, 5, 225, 325, -10));
		initialInventory.add(new ColdItem("frozen meat", 10, 14, 450, 575, -14));
		initialInventory.add(new ColdItem("frozen vegetable mix", 5, 8, 225, 325, -12));

		double expectedInitialCapital = 100000;
		String storeName = "SuperMart";

		assertEquals("initial capital should be 100,000", expectedInitialCapital, initialStore.getCapital(), PRECISION);
		assertEquals("initial inventory should all items with 0 amount", initialInventory,
				initialStore.getInventory());
		assertEquals("initial name should be SuperMart", storeName, initialStore.getName());
	}

	@Before
	public void setUp() throws Exception {
		Store store = Store.getInstance();
		store.reset();
		
		double expectedInitialCapital = 100000;
		String storeName = "SuperMart";
		
		assertEquals("initial capital should be 100,000", expectedInitialCapital, store.getCapital(), PRECISION);
		assertEquals("initial name should be SuperMart", storeName, store.getName());
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
	public void testStoreName() {
		Store store = Store.getInstance();
		String name = "SuperMart";
		assertEquals("set store name failed", name, store.getName());

		String newName = "NotSuperMart";
		store.setName(newName);
		assertEquals("set store name failed", newName, store.getName());
	}
	
	@Test
	public void testGenerateOrderNeeded() {
		Store store = Store.getInstance();
		store.generateOrder();
	}
	
	@Test
	public void testGenerateOrderNotNeeded() {
		
	}
	
	@Test
	public void testProcessSale() {
		
	}

//	@Test
//	public void testStoreUpdateInventoryEmptyManifest() throws StockException {
//		Store store = Store.getInstance();
//		double expectedCapital = store.getCapital();
//		Stock expectedInventory = store.getInventory();
//		String name = store.getName();
//
//		Stock order = new Stock(StockType.StockOrders);
//
//		Manifest manifest = new Manifest(order);
//		store.updateInventory(manifest);
//
//		assertEquals("empty manifest shouldn't change store's capital", expectedCapital, store.getCapital(), PRECISION);
//		assertEquals("empty manifest shouldn't change store's inventory", expectedInventory, store.getInventory());
//		assertEquals("empty manifest shouldn't change store's name", name, store.getName());
//	}

//	@Test
//	public void testStoreUpdateInventory() throws StockException {
//		Store store = Store.getInstance();
//		double expectedCapital = store.getCapital();
//		String name = store.getName();
//
//		Stock order = new Stock(StockType.StockOrders);
//
//		MockItem rice = MockItem.ITEM_RICE;
//		rice.reorder();
//		order.add(rice.getItem());
//
//		MockItem mushroom = MockItem.ITEM_MUSHROOM;
//		mushroom.reorder();
//		order.add(mushroom.getItem());
//
//		Manifest manifest = new Manifest(order);
//		expectedCapital -= manifest.getTotalCost();
//		Stock expectedInventory = manifest.getOrder();
//
//		store.updateInventory(manifest);
//		assertEquals("capital should decrease", expectedCapital, store.getCapital(), PRECISION);
//		assertEquals("inventory should change according to manifest", expectedInventory, store.getInventory());
//		assertEquals("name should not change", name, store.getName());
//	}

}
