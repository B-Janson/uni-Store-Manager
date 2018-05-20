package main.java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utilities class used for assorted useful methods such as reading and writing
 * CSV as well as rounding decimal numbers
 * 
 * @author Brandon Janson
 */
public class Utilities {

	/**
	 * Takes a file name as input and returns a string array Each element in the
	 * return string is one line of the file
	 * 
	 * @param fileName
	 *            project context name of the file to read
	 * @return string array, each element being one line in the file
	 * @throws IOException
	 *             catch this in the front end
	 */
	public static String[] readCSV(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		StringBuilder sBuilder = new StringBuilder();

		String line;
		while ((line = in.readLine()) != null) {
			sBuilder.append(line + "\n");
		}
		in.close();

		return sBuilder.toString().split("\n");
	}

	/**
	 * Writes a string to the specified file name
	 * 
	 * @param fileName
	 *            name of the file to write to
	 * @param content
	 *            content to write to the file
	 * @return true if successful, false otherwise
	 * @throws IOException
	 *             if cannot find file or any other IOException
	 */
	public static boolean writeCSV(String fileName, String content) throws IOException {
		File output = new File(fileName);
		if (!output.exists()) {
			output.createNewFile();
		}
		PrintWriter pWriter = new PrintWriter(output);
		pWriter.write(content);
		pWriter.close();
		return true;
	}

	/**
	 * Function used to round a double to the specified number of places. This is
	 * used mainly for tests when generating random numbers, as there is no
	 * requirement for any precision past 2 decimal places.
	 * 
	 * @param input
	 *            the number to round
	 * @param places
	 *            the number of places to round to
	 * @return new double value that has been rounded to the specified number of
	 *         decimal places
	 */
	public static double roundTo(double input, int places) {
		BigDecimal bDecimal = new BigDecimal(input);
		bDecimal = bDecimal.setScale(places, RoundingMode.HALF_UP);
		return bDecimal.doubleValue();
	}

}
