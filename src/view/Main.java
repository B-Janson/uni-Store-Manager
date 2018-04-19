package view;

import java.io.IOException;

import controller.Utilities;

public class Main {

	public static void main(String[] args) {
		try {
			Utilities.readCSV("test.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
