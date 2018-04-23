package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Brandon Janson
 *
 */
public class Utilities {
	
	/**
	 * Takes a file name as input and returns a string array
	 * Each element in the return string is one line of the file
	 * @param fileName project context name of the file to read
	 * @return string array, each element being one line in the file
	 * @throws IOException catch this in the front end
	 */
	public static String[] readCSV(String fileName) throws IOException {
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
		
		return sBuilder.toString().split("\n");
	}
	
	public static boolean writeCSV(String fileName, String content) {
		return false;
	}

}
