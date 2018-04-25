package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.stock.Stock;

public class TestTrucks {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOrdinaryTruckCost() {
		OrdinaryTruck testTruck = new OrdinaryTruck(cost, capacity, cargo);
		assertEquals(0, testTruck.getCost());
	}

	@Test
	public void testOrdinaryTruckCapacity() {
		double cost = 500;
		int capacity = 0;
		Stock cargo = new Cargo();
		OrdinaryTruck testTruck = new OrdinaryTruck(cost, capacity, cargo);
		assertEquals(1000, testTruck.getCapacity());
	}

}
