package main.java.exceptions;

/**
 * Exception used when a delivery was unsuccessful
 * @author Brandon Janson
 *
 */
public class DeliveryException extends Exception {

	/**
	 * Generated serialVersionUID -- necessary for Execption class
	 */
	private static final long serialVersionUID = -2461644881918330806L;

	public DeliveryException() {
		// TODO Auto-generated constructor stub
	}

	public DeliveryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DeliveryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DeliveryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DeliveryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
