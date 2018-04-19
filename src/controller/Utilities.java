package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {
	
	public static String readCSV(String fileName) throws IOException {
		// To read the file
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		// To construct the output string
		StringBuilder sBuilder = new StringBuilder();
		
		// To store each line that is read
		String line;
		while ((line = in.readLine()) != null) {
			sBuilder.append(line + "\n");
		}
		// Close file reader to prevent leaking resources
		in.close();
		return sBuilder.toString();
	}
	
	public static boolean writeCSV(String fileName, String content) {
		return false;
	}

}
