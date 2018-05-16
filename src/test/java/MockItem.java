package test.java;

import main.java.exceptions.StockException;
import main.java.stock.ColdItem;
import main.java.stock.Item;

/**
 * Static implementation to use pre-made items for tests
 * 
 * @author Brandon Janson
 */
public class MockItem {

	public static final int NUM_NORMAL_ITEMS = 4;
	public static final int NUM_COLD_ITEMS = 4;

	/**
	 * static rice item used for testing
	 */
	public static MockItem ITEM_RICE = new MockItem(MockItemType.RICE);
	/**
	 * static rice item used for testing
	 */
	public static MockItem ITEM_BEANS = new MockItem(MockItemType.BEANS);
	/**
	 * static rice item used for testing
	 */
	public static MockItem ITEM_PASTA = new MockItem(MockItemType.PASTA);
	/**
	 * static rice item used for testing
	 */
	public static MockItem BISCUITS = new MockItem(MockItemType.BISCUITS);
	/**
	 * static rice item used for testing
	 */
	public static MockItem ITEM_NUTS = new MockItem(MockItemType.NUTS);
	/**
	 * static rice item used for testing
	 */
	public static MockItem ITEM_CHIPS = new MockItem(MockItemType.CHIPS);
	/**
	 * static rice item used for testing
	 */
	public static MockItem ITEM_CHOCOLATE = new MockItem(MockItemType.CHOCOLATE);
	/**
	 * static rice item used for testing
	 */
	public static MockItem ITEM_BREAD = new MockItem(MockItemType.BREAD);

	
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_MUSHROOMS = new MockItem(MockItemType.MUSHROOMS);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_TOMATOES = new MockItem(MockItemType.TOMATOES);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_LETTUCE = new MockItem(MockItemType.LETTUCE);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_GRAPES = new MockItem(MockItemType.GRAPES);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_ASPARAGUS = new MockItem(MockItemType.ASPARAGUS);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_CELERY = new MockItem(MockItemType.CELERY);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_CHICKEN = new MockItem(MockItemType.CHICKEN);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_BEEF = new MockItem(MockItemType.BEEF);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_FISH = new MockItem(MockItemType.FISH);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_YOGHURT = new MockItem(MockItemType.YOGHURT);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_MILK = new MockItem(MockItemType.MILK);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_CHEESE = new MockItem(MockItemType.CHEESE);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_ICE_CREAM = new MockItem(MockItemType.ICE_CREAM);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_ICE = new MockItem(MockItemType.ICE);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_FROZEN_MEAT = new MockItem(MockItemType.FROZEN_MEAT);
	/**
	 * static mushroom item used for testing
	 */
	public static MockItem ITEM_FROZEN_VEGETABLE_MIX = new MockItem(MockItemType.FROZEN_VEGETABLE_MIX);

	/**
	 * enum used to determine the values used for the mock items this is based on
	 * the given csv file item_properties
	 * 
	 * @author Brandon Janson
	 */
	public enum MockItemType {
		RICE, BEANS, PASTA, BISCUITS, NUTS, CHIPS, CHOCOLATE, BREAD,

		MUSHROOMS, TOMATOES, LETTUCE, GRAPES, ASPARAGUS, CELERY, CHICKEN, BEEF, FISH, YOGHURT, MILK, CHEESE, ICE_CREAM, ICE, FROZEN_MEAT, FROZEN_VEGETABLE_MIX
	};

	private Item item;

	/**
	 * Constructor for making mock items for testing purposes
	 * 
	 * @param type
	 *            type of item to return
	 */
	public MockItem(MockItemType type) {
		switch (type) {
		case RICE:
			item = new Item("rice", 2, 3, 225, 300);
			break;
		case BEANS:
			item = new Item("beans", 4, 6, 450, 525);
			break;
		case PASTA:
			item = new Item("pasta", 3, 4, 125, 250);
			break;
		case BISCUITS:
			item = new Item("biscuits", 2, 5, 450, 575);
			break;
		case NUTS:
			item = new Item("nuts", 5, 9, 125, 250);
			break;
		case CHIPS:
			item = new Item("chips", 2, 4, 125, 200);
			break;
		case CHOCOLATE:
			item = new Item("chocolate", 5, 8, 250, 375);
			break;
		case BREAD:
			item = new Item("bread", 2, 3, 125, 200);
			break;
			
		case MUSHROOMS:
			item = new ColdItem("mushrooms", 2, 4, 200, 325, 10);
			break;
		case TOMATOES:
			item = new ColdItem("tomatoes", 1, 2, 325, 400, 10);
			break;
		case ICE_CREAM:
			item = new ColdItem("ice cream", 8, 14, 175, 250, -20);
			break;
		case GRAPES:
			item = new ColdItem("grapes", 4, 6, 125, 225, 9);
			break;
		case ASPARAGUS:
			item = new ColdItem("asparagus", 2, 4, 175, 275, 8);
			break;
		case BEEF:
			item = new ColdItem("beef", 12, 17, 425, 550, 3);
			break;
		case CELERY:
			item = new ColdItem("celery", 2, 3, 225, 350, 8);
			break;
		case CHEESE:
			item = new ColdItem("cheese", 4, 7, 375, 450, 3);
			break;
		case CHICKEN:
			item = new ColdItem("chicken", 10, 14, 325, 425, 4);
			break;
		case FISH:
			item = new ColdItem("fish", 13, 16, 375, 475, 2);
			break;
		case FROZEN_MEAT:
			item = new ColdItem("frozen meat", 10, 14, 450, 575, -14);
			break;
		case FROZEN_VEGETABLE_MIX:
			item = new ColdItem("frozen vegetable mix", 5, 8, 225, 325, -12);
			break;
		case ICE:
			item = new ColdItem("ice", 2, 5, 225, 325, -10);
			break;
		case LETTUCE:
			item = new ColdItem("lettuce", 1, 2, 250, 350, 10);
			break;
		case MILK:
			item = new ColdItem("milk", 2, 3, 300, 425, 3);
			break;
		case YOGHURT:
			item = new ColdItem("yoghurt", 10, 12, 200, 325, 3);
			break;
		default:
			break;
		}
	}

	/**
	 * returns item generated by this mock item wrapper
	 * 
	 * @return item as specified in constructor
	 */
	public Item getItem() {
		return item;
	}

	public static Item[] getNormalItems() {
		Item[] normalItems = new Item[NUM_NORMAL_ITEMS];
		normalItems[0] = new MockItem(MockItemType.RICE).getItem();
		normalItems[1] = new MockItem(MockItemType.BEANS).getItem();
		normalItems[2] = new MockItem(MockItemType.PASTA).getItem();
		normalItems[3] = new MockItem(MockItemType.BISCUITS).getItem();

		return normalItems;
	}

	public static Item[] getColdItems() {
		Item[] coldItems = new Item[NUM_COLD_ITEMS];
		coldItems[0] = new MockItem(MockItemType.MUSHROOMS).getItem();
		coldItems[1] = new MockItem(MockItemType.TOMATOES).getItem();
		coldItems[2] = new MockItem(MockItemType.ICE_CREAM).getItem();
		coldItems[3] = new MockItem(MockItemType.GRAPES).getItem();

		return coldItems;
	}

	public static Item[] getAllMockItems() {
		Item[] items = new Item[MockItemType.values().length];
		int i = 0;

		for (MockItemType type : MockItemType.values()) {
			items[i] = new MockItem(type).getItem();
			i++;
		}

		return items;
	}

	/**
	 * method used to set the chosen item's amount to be its reorder amount used for
	 * simulating store orders
	 */
	public void reorder() {
		try {
			item.setCurrentAmount(item.getReorderAmount());
		} catch (StockException e) {
			e.printStackTrace();
		}
	}

}
