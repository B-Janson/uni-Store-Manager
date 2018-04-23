package main.java.view;

import java.io.IOException;

import main.java.controller.Utilities;

public class Main {

	public static void main(String[] args) {
		try {
			String[] testStrings = Utilities.readCSV("item_properties.csv");
			for (String string : testStrings) {
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
