package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.delivery.ColdTruck;
import main.java.delivery.Manifest;
import main.java.delivery.OrdinaryTruck;
import main.java.delivery.Truck;
import main.java.stock.ColdItem;
import main.java.stock.Item;

/**
 * @author Chris Martin
 *
 */
public class TestManifest {

	private final double PRECISION = 0.01;

	private Manifest manifest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		manifest = new Manifest();

	}

	/**
	 * Test method for {@link main.java.delivery.Manifest#getTotalCost()}.
	 */
	@Test
	public void testGetTotalCostEmptyManifest() {
		assertEquals(0, manifest.getTotalCost(), PRECISION);
	}

	/**
	 * Test method for {@link main.java.delivery.Manifest#getTotalCost()}.
	 */
	@Test
	public void testGetTotalCostOneNormalItem() {
		Item newItem = MockItem.getRandomNormalItem();
		newItem.setToReorder();
		manifest.addItem(newItem);

		Truck newTruck = new OrdinaryTruck();
		newTruck.addItem(newItem);

		assertEquals(newTruck.getCost(), manifest.getTotalCost(), PRECISION);
	}

	/**
	 * Test method for {@link main.java.delivery.Manifest#getTotalCost()}.
	 */
	@Test
	public void testGetTotalCostOneColdItem() {
		ColdItem newItem = MockItem.getRandomColdItem();
		newItem.setToReorder();
		manifest.addItem(newItem);

		Truck newTruck = new ColdTruck(newItem.getTemperature());
		newTruck.addItem(newItem);

		assertEquals(newTruck.getCost(), manifest.getTotalCost(), PRECISION);
	}

	/**
	 * Test method for {@link main.java.delivery.Manifest#getTotalCost()}.
	 */
	@Test
	public void testGetTotalCostColdAndNormal() {
		Item newItem = MockItem.getRandomNormalItem();
		newItem.setToReorder();
		manifest.addItem(newItem);

		ColdItem coldItem = MockItem.getRandomColdItem();
		coldItem.setToReorder();
		manifest.addItem(coldItem);

		Truck newTruck = new OrdinaryTruck();
		newTruck.addItem(newItem);

		Truck coldTruck = new ColdTruck(coldItem.getTemperature());
		coldTruck.addItem(coldItem);

		assertEquals(newTruck.getCost() + coldTruck.getCost(), manifest.getTotalCost(), PRECISION);
	}

	/**
	 * Test method for {@link main.java.delivery.Manifest#getTrucks()}.
	 */
	@Test
	public void testGetTrucks() {
		Item newItem = MockItem.getRandomNormalItem();
		newItem.setToReorder();
		manifest.addItem(newItem);

		ColdItem coldItem = MockItem.getRandomColdItem();
		coldItem.setToReorder();
		manifest.addItem(coldItem);

		Truck newTruck = new OrdinaryTruck();
		newTruck.addItem(newItem);

		Truck coldTruck = new ColdTruck(coldItem.getTemperature());
		coldTruck.addItem(coldItem);

		ArrayList<Truck> expectedTrucks = new ArrayList<>();
		expectedTrucks.add(coldTruck);
		expectedTrucks.add(newTruck);
		
		ArrayList<Truck> actualTrucks = manifest.getTrucks();
		
		for (int i = 0; i < actualTrucks.size(); i++) {
			assertEquals(actualTrucks.get(i).toString(), expectedTrucks.get(i).toString());
		}
	}

}
