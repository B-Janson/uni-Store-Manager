package main.java.exceptions;

/**
 * Useful exception class for when files cannot be read, or are in an incorrect
 * format
 * 
 * @author Brandon Janson
 *
 */
public class CSVException extends Exception {

	/**
	 * Generated serialVersionUID -- necessary for Execption class
	 */
	private static final long serialVersionUID = 4498923210856881242L;

	public CSVException() {
		// TODO Auto-generated constructor stub
	}

	public CSVException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CSVException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CSVException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CSVException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
