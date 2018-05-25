package test.java;

import java.util.Random;

import main.java.stock.ColdItem;
import main.java.stock.Item;

/**
 * Static implementation to use pre-made items for tests
 * 
 * @author Brandon Janson
 */
public class MockItem {

	private static final int NUM_NORMAL_ITEMS = 8;
	private static final int NUM_COLD_ITEMS = 16;

	private static Item[] normalItems;
	private static ColdItem[] coldItems;

	public static final int ITEM_RICE = 0;
	public static final int ITEM_BEANS = 1;
	public static final int ITEM_PASTA = 2;
	public static final int ITEM_BISCUITS = 3;
	public static final int ITEM_NUTS = 4;
	public static final int ITEM_CHIPS = 5;
	public static final int ITEM_CHOCOLATE = 6;
	public static final int ITEM_BREAD = 7;

	public static final int ITEM_MUSHROOMS = 8;
	public static final int ITEM_TOMATOES = 9;
	public static final int ITEM_ICE_CREAM = 10;
	public static final int ITEM_GRAPES = 11;
	public static final int ITEM_ASPARAGUS = 12;
	public static final int ITEM_BEEF = 13;
	public static final int ITEM_CELERY = 14;
	public static final int ITEM_CHEESE = 15;
	public static final int ITEM_CHICKEN = 16;
	public static final int ITEM_FISH = 17;
	public static final int ITEM_FROZEN_MEAT = 18;
	public static final int ITEM_FROZEN_VEGETABLE_MIX = 19;
	public static final int ITEM_ICE = 20;
	public static final int ITEM_LETTUCE = 21;
	public static final int ITEM_MILK = 22;
	public static final int ITEM_YOGHURT = 23;

	public static Item[] getNormalItems() {
		resetAllItems();
		return normalItems;
	}

	public static Item[] getColdItems() {
		resetAllItems();
		return coldItems;
	}

	public static Item[] getAllMockItems() {
		resetAllItems();
		Item[] items = new Item[NUM_COLD_ITEMS + NUM_NORMAL_ITEMS];
		for (int i = 0; i < normalItems.length; i++) {
			items[i] = normalItems[i];
		}

		for (int i = 0; i < coldItems.length; i++) {
			items[i + normalItems.length] = coldItems[i];
		}

		return items;
	}

	public static Item getRandomNormalItem() {
		resetAllItems();
		Random random = new Random();
		return normalItems[random.nextInt(NUM_NORMAL_ITEMS)];
	}

	public static ColdItem getRandomColdItem() {
		resetAllItems();
		Random random = new Random();
		return coldItems[random.nextInt(NUM_COLD_ITEMS)];
	}

	public static Item[] getRandomItems(int numItems) {
		resetAllItems();
		Random random = new Random();
		Item[] possibleItems = getAllMockItems();
		Item[] retItems = new Item[numItems];
		for (int i = 0; i < numItems; i++) {
			Item randItem = null;
			do {
				randItem = possibleItems[random.nextInt(possibleItems.length)];
			} while (inArray(retItems, randItem));

			retItems[i] = randItem;
		}
		return retItems;
	}

	private static boolean inArray(Item[] array, Item item) {
		if (item == null) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i].getName().equals(item.getName())) {
				return true;
			}
		}
		return false;
	}

	private static void resetAllItems() {
		normalItems = new Item[] { new Item("rice", 2, 3, 225, 300), new Item("beans", 4, 6, 450, 525),
				new Item("pasta", 3, 4, 125, 250), new Item("biscuits", 2, 5, 450, 575),
				new Item("nuts", 5, 9, 125, 250), new Item("chips", 2, 4, 125, 200),
				new Item("chocolate", 5, 8, 250, 375), new Item("bread", 2, 3, 125, 200) };

		coldItems = new ColdItem[] { new ColdItem("mushrooms", 2, 4, 200, 325, 10),
				new ColdItem("tomatoes", 1, 2, 325, 400, 10), new ColdItem("ice cream", 8, 14, 175, 250, -20),
				new ColdItem("grapes", 4, 6, 125, 225, 9), new ColdItem("asparagus", 2, 4, 175, 275, 8),
				new ColdItem("beef", 12, 17, 425, 550, 3), new ColdItem("celery", 2, 3, 225, 350, 8),
				new ColdItem("cheese", 4, 7, 375, 450, 3), new ColdItem("chicken", 10, 14, 325, 425, 4),
				new ColdItem("fish", 13, 16, 375, 475, 2), new ColdItem("frozen meat", 10, 14, 450, 575, -14),
				new ColdItem("frozen vegetable mix", 5, 8, 225, 325, -12), new ColdItem("ice", 2, 5, 225, 325, -10),
				new ColdItem("lettuce", 1, 2, 250, 350, 10), new ColdItem("milk", 2, 3, 300, 425, 3),
				new ColdItem("yoghurt", 10, 12, 200, 325, 3) };
	}

}
