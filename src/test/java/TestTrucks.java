package test.java;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.java.controller.Utilities;
import main.java.delivery.ColdTruck;
import main.java.delivery.OrdinaryTruck;
import main.java.delivery.Truck;
import main.java.stock.ColdItem;
import main.java.stock.Item;
import main.java.stock.Stock;
import main.java.stock.StockType;

public class TestTrucks {

	private static final int ORDINARY_TRUCK_CAPACITY = 1000;
	private static final int COLD_TRUCK_CAPACITY = 800;

	private static final double PRECISION = 0.01;
	private static final int DECIMAL_PLACES = 2;

	private static final int TEMPERATURE_RANGE = 100;

	private Random random;

	private Stock cargo;
	private Item rice;
	private Item mushroom;

	private double temperature;

	/**
	 * @throws Exception
	 * @author Brandon Janson
	 */
	@Before
	public void setUp() throws Exception {
		// set up rng
		random = new Random();
		// set up empty cargo
		cargo = new Stock(StockType.TruckCargo);

		// set up random temperature
		double temp = random.nextDouble() * random.nextInt(TEMPERATURE_RANGE) - TEMPERATURE_RANGE / 2;
		temperature = Utilities.roundTo(temp, DECIMAL_PLACES);
		assertEquals("this is failing", temp, temperature, PRECISION);
		
		// set up test items for adding to cargo
		// TODO refactor this out to just one method
		String[] riceProperties = "rice,2,3,225,300".split(",");
		String name = riceProperties[0];
		double cost = Double.parseDouble(riceProperties[1]);
		double price = Double.parseDouble(riceProperties[2]);
		int reorderPoint = Integer.parseInt(riceProperties[3]);
		int reorderAmount = Integer.parseInt(riceProperties[4]);
		rice = new Item(name, cost, price, reorderPoint, reorderAmount);
		
		String[] mushroomProperties = "mushrooms,2,4,200,325,10".split(",");
		name = mushroomProperties[0];
		cost = Double.parseDouble(mushroomProperties[1]);
		price = Double.parseDouble(mushroomProperties[1]);
		reorderPoint = Integer.parseInt(mushroomProperties[3]);
		reorderAmount = Integer.parseInt(mushroomProperties[4]);
		double temperature = Double.parseDouble(mushroomProperties[5]);
		mushroom = new ColdItem(name, cost, price, reorderPoint, reorderAmount, temperature);
	}

	@Test
	public void testOrdinaryTruckCost() {
		Truck testTruck = new OrdinaryTruck(cargo);
		// TODO calculate expected cost of truck
		double expected = 0.0;
		assertEquals("cost of truck incorrect", expected, testTruck.getCost(), PRECISION);
	}

	@Test
	public void testOrdinaryTruckCapacity() {
		Truck testTruck = new OrdinaryTruck(cargo);
		assertEquals("initial capacity of truck incorrect", ORDINARY_TRUCK_CAPACITY, testTruck.getCapacity());
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testColdTruckCost() {
		Truck testTruck = new ColdTruck(cargo, temperature);
		int baseCost = 900;
		int modifer = 200;
		double tempRate = 0.7;
		int tempScale = 5;
		double expected = baseCost + modifer * Math.pow(tempRate, temperature / tempScale);
		assertEquals("cost of truck incorrect", expected, testTruck.getCost(), PRECISION);
	}

	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testColdTruckCapacity() {
		Truck testTruck = new ColdTruck(cargo, temperature);
		assertEquals("initial capacity of truck incorrect", COLD_TRUCK_CAPACITY, testTruck.getCapacity());
	}
	
	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testColdTruckTemperature() {
		ColdTruck testTruck = new ColdTruck(cargo, temperature);
		assertEquals("initial temperature of cold truck incorrect", temperature, testTruck.getTemperature(), PRECISION);
		
		double testTemperature = random.nextDouble() * random.nextInt(TEMPERATURE_RANGE) - TEMPERATURE_RANGE / 2;
		testTruck.setTemperature(testTemperature);
		assertEquals("set/getTemperature of cold truck incorrect", testTemperature, testTruck.getTemperature(), PRECISION);
	}
	
	/**
	 * @author Brandon Janson
	 */
	@Test
	public void testColdTruckCargo() {
		Truck testTruck = new ColdTruck(cargo, temperature);
		assertEquals("initial cargo of cold truck incorrect", cargo, testTruck.getCargo());
		
		Stock testCargo = new Stock(StockType.TruckCargo);
		testCargo.add(rice);
		testCargo.add(mushroom);
		
		testTruck.setCargo(testCargo);
		assertEquals("set/getCargo of cold truck incorrect", testCargo, testTruck.getCargo());
	}

}
