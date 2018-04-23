package view;

import java.io.IOException;
import java.util.Random;

import controller.Utilities;

public class Main {

	public static void main(String[] args) {
		try {
			String[] testStrings = Utilities.readCSV("test.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
