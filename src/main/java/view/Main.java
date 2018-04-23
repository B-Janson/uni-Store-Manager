package main.java.view;

import java.io.IOException;

import main.java.controller.Utilities;
import main.java.data.ColdItem;
import main.java.data.Item;

public class Main {

	public static void main(String[] args) {
		try {
			String[] testStrings = Utilities.readCSV("item_properties.csv");
			for (String string : testStrings) {
				System.out.println(string);
			}

			Utilities.writeCSV("newtest.csv", "test,test");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Item newItem = new ColdItem("apple", 10, 12, 200, 250, -10);
		if (newItem instanceof ColdItem) {
			System.out.println("Works");
		}
	}

}
