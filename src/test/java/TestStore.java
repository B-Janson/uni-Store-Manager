package test.java;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.controller.Store;
import main.java.exceptions.CSVException;
import main.java.exceptions.StockException;
import main.java.stock.Item;
import main.java.stock.Stock;

public class TestStore {

	private static final double PRECISION = 0.01;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Store initialStore = Store.getInstance();
		initialStore.reset();

		Stock initialInventory = new Stock();

		Item[] items = MockItem.getAllMockItems();

		for (Item item : items) {
			initialInventory.add(item);
		}

		double expectedInitialCapital = 100000;
		String storeName = "SuperMart";

		assertEquals("initial capital should be 100,000", expectedInitialCapital, initialStore.getCapital(), PRECISION);
		assertEquals("initial inventory should all items with 0 amount", initialInventory, initialStore.getInventory());
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

		Item[] items = MockItem.getAllMockItems();

		for (Item item : items) {
			item.setToReorder();
			expectedInventory.add(item);
		}

//		double expectedCapital = 42717.88;
		double expectedCapital = 42067.44;

		assertEquals(expectedInventory, store.getInventory());
		assertEquals(expectedCapital, store.getCapital(), PRECISION);
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
	public void testProcessSale0() throws StockException, IOException, CSVException {
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

	@Test(expected = StockException.class)
	public void testProcessSale0Then1() throws StockException, IOException, CSVException {
		Store store = Store.getInstance();
		store.generateOrder();
		store.doSale("sales_log_0.csv");
		store.doSale("sales_log_1.csv");
	}

}
