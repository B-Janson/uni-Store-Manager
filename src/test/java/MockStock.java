package test.java;

import main.java.exceptions.StockException;
import main.java.stock.Item;
import main.java.stock.Stock;

public class MockStock {
	
	public static final int REORDER_AMOUNT = -1;
	
	private Stock stock;
	private MockStockType type;
	private int stockAmount;

	public enum MockStockType {
		NORMAL_ITEMS,
		COLD_ITEMS,
		MIXED_ITEMS,
		NO_ITEMS
	}

	public MockStock(MockStockType type, int amount) {
		this.type = type;
		this.stockAmount = amount;
		stock = new Stock();
	}
	
	public Stock getStock() throws StockException {
		Item[] items;
		switch (type) {
		case NORMAL_ITEMS:
			items = MockItem.getNormalItems();
			break;
		case COLD_ITEMS:
			items = MockItem.getColdItems();
			break;
		case MIXED_ITEMS:
			items = MockItem.getAllMockItems();
			break;
		case NO_ITEMS:
		default:
			items = new Item[0];
			break;
		}
		
		for (Item item : items) {
			int amount = (stockAmount == REORDER_AMOUNT) ? item.getReorderAmount() : stockAmount;
			item.setCurrentAmount(amount);
			stock.add(item);
		}
		
		return stock;
	}

}
