package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.delivery.OrdinaryTruck;
import main.java.stock.Stock;
import main.java.stock.StockType;

public class TestTrucks {
	
	Stock cargo;

	@Before
	public void setUp() throws Exception {
		cargo = new Stock(StockType.TruckCargo);
	}

	@Test
	public void testOrdinaryTruckCost() {
		int capacity = 1000;
		double acceptedDelta = 0.01;
		OrdinaryTruck testTruck = new OrdinaryTruck(capacity, cargo);
		double expected = 0.0;
		assertEquals("cost of truck incorrect", expected, testTruck.getCost(), acceptedDelta);
	}

	@Test
	public void testOrdinaryTruckCapacity() {
		int capacity = 0;
		OrdinaryTruck testTruck = new OrdinaryTruck(capacity, cargo);
		int expected = 1000;
		assertEquals("initial capacity of truck incorrect", expected, testTruck.getCapacity());
	}

}
