package test.java;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.java.controller.Utilities;
import main.java.delivery.ColdTruck;
import main.java.delivery.OrdinaryTruck;
import main.java.delivery.Truck;
import main.java.stock.Stock;
import main.java.stock.StockType;

public class TestTrucks {

	private static final int ORDINARY_TRUCK_CAPACITY = 1000;
	private static final int COLD_TRUCK_CAPACITY = 800;

	private static final double FLOATING_POINT_TOLERANCE = 0.001;
	private static final int DECIMAL_PLACES = 2;

	private static final int TEMPERATURE_RANGE = 100;

	private Random random;

	private Stock cargo;

	private double temperature;

	@Before
	public void setUp() throws Exception {
		random = new Random();
		cargo = new Stock(StockType.TruckCargo);

		double temp = random.nextDouble() * random.nextInt(TEMPERATURE_RANGE) - TEMPERATURE_RANGE / 2;
		temperature = Utilities.roundTo(temp, DECIMAL_PLACES);
	}

	@Test
	public void testOrdinaryTruckCost() {
		Truck testTruck = new OrdinaryTruck(cargo);
		// TODO calculate expected cost of truck
		double expected = 0.0;
		assertEquals("cost of truck incorrect", expected, testTruck.getCost(), FLOATING_POINT_TOLERANCE);
	}

	@Test
	public void testOrdinaryTruckCapacity() {
		Truck testTruck = new OrdinaryTruck(cargo);
		assertEquals("initial capacity of truck incorrect", ORDINARY_TRUCK_CAPACITY, testTruck.getCapacity());
	}

	@Test
	public void testColdTruckCost() {
		Truck testTruck = new ColdTruck(cargo, temperature);
		int baseCost = 900;
		int modifer = 200;
		double tempRate = 0.7;
		int tempScale = 5;
		double expected = baseCost + modifer * Math.pow(tempRate, temperature / tempScale);
		assertEquals("cost of truck incorrect", expected, testTruck.getCost(), FLOATING_POINT_TOLERANCE);
	}

	@Test
	public void testColdTruckCapacity() {
		Truck testTruck = new ColdTruck(cargo, temperature);
		assertEquals("initial capacity of truck incorrect", COLD_TRUCK_CAPACITY, testTruck.getCapacity());
	}

}
