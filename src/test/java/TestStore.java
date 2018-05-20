package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.controller.Store;
import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;

public class TestStore {

	private static final double PRECISION = 0.01;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Store initialStore = Store.getInstance();
		Stock initialInventory = new Stock();

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
	public void testGenerateOrderNeeded() throws IOException, StockException {
		Store store = Store.getInstance();
		store.generateOrder();
		
		Stock expectedInventory = new Stock();
		
		expectedInventory.add(new Item("rice", 2, 3, 225, 300));
		expectedInventory.add(new Item("beans", 4, 6, 450, 525));
		expectedInventory.add(new Item("pasta", 3, 4, 125, 250));
		expectedInventory.add(new Item("biscuits", 2, 5, 450, 575));
		expectedInventory.add(new Item("nuts", 5, 9, 125, 250));
		expectedInventory.add(new Item("chips", 2, 4, 125, 200));
		expectedInventory.add(new Item("chocolate", 5, 8, 250, 375));
		expectedInventory.add(new Item("bread", 2, 3, 125, 200));

		expectedInventory.add(new ColdItem("mushrooms", 2, 4, 200, 325, 10));
		expectedInventory.add(new ColdItem("tomatoes", 1, 2, 325, 400, 10));
		expectedInventory.add(new ColdItem("lettuce", 1, 2, 250, 350, 10));
		expectedInventory.add(new ColdItem("grapes", 4, 6, 125, 225, 9));
		expectedInventory.add(new ColdItem("asparagus", 2, 4, 175, 275, 8));
		expectedInventory.add(new ColdItem("celery", 2, 3, 225, 350, 8));
		expectedInventory.add(new ColdItem("chicken", 10, 14, 325, 425, 4));
		expectedInventory.add(new ColdItem("beef", 12, 17, 425, 550, 3));
		expectedInventory.add(new ColdItem("fish", 13, 16, 375, 475, 2));
		expectedInventory.add(new ColdItem("yoghurt", 10, 12, 200, 325, 3));
		expectedInventory.add(new ColdItem("milk", 2, 3, 300, 425, 3));
		expectedInventory.add(new ColdItem("cheese", 4, 7, 375, 450, 3));
		expectedInventory.add(new ColdItem("ice cream", 8, 14, 175, 250, -20));
		expectedInventory.add(new ColdItem("ice", 2, 5, 225, 325, -10));
		expectedInventory.add(new ColdItem("frozen meat", 10, 14, 450, 575, -14));
		expectedInventory.add(new ColdItem("frozen vegetable mix", 5, 8, 225, 325, -12));
		
		for (Item item : expectedInventory.getItems()) {
			item.setToReorder();
		}
		
		double expectedCapital = 42717.88;
		
		assertEquals(expectedCapital, store.getCapital(), PRECISION);
		assertEquals(expectedInventory, store.getInventory());
	}
	
	@Test
	public void testGenerateOrderNotNeeded() throws IOException, StockException {
		Store store = Store.getInstance();
		store.generateOrder();
		
		double expectedCapital = store.getCapital();
		
		Stock updatedInventory = store.getInventory();
		store.generateOrder();
		
		assertEquals(expectedCapital, store.getCapital(), PRECISION);
		assertEquals(updatedInventory, store.getInventory());
	}
	
	@Test
	public void testProcessSale0() throws StockException, IOException {
		Store store = Store.getInstance();
		store.generateOrder();
		double expectedCapital = store.getCapital();
		store.doSale("sales_log_0.csv");
		
		Stock updatedInventory = new Stock();
		
		Item[] allItems = MockItem.getAllMockItems();
		
		Item item = allItems[MockItem.ITEM_RICE];
		int sales = 88;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_BEANS];
		sales = 423;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_PASTA];
		sales = 43;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_BISCUITS];
		sales = 394;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_NUTS];
		sales = 36;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_CHIPS];
		sales = 44;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_CHOCOLATE];
		sales = 93;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_BREAD];
		sales = 95;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_MUSHROOMS];
		sales = 176;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_TOMATOES];
		sales = 164;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_LETTUCE];
		sales = 152;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_GRAPES];
		sales = 115;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_ASPARAGUS];
		sales = 118;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_CELERY];
		sales = 84;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_CHICKEN];
		sales = 139;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_BEEF];
		sales = 195;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_FISH];
		sales = 368;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_YOGHURT];
		sales = 51;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_MILK];
		sales = 113;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_CHEESE];
		sales = 349;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_ICE_CREAM];
		sales = 88;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_ICE];
		sales = 76;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_FROZEN_MEAT];
		sales = 220;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		item = allItems[MockItem.ITEM_FROZEN_VEGETABLE_MIX];
		sales = 109;
		item.setCurrentAmount(item.getReorderAmount() - sales);
		updatedInventory.add(item);
		expectedCapital += sales * item.getPrice();
		
		assertEquals(expectedCapital, store.getCapital(), PRECISION);
		assertEquals(updatedInventory, store.getInventory());
	}
	
	@Test(expected=StockException.class)
	public void testProcessSale0Then1() throws StockException, IOException {
		Store store = Store.getInstance();
		store.generateOrder();
		store.doSale("sales_log_0.csv");
		store.doSale("sales_log_1.csv");
	}

}
