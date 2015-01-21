package br.com.jcp.xyinc.ejb.servicelocator;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Design Pattern Service Locator implementation.
 */
public class ServiceLocatorException extends RuntimeException {

	private static final long serialVersionUID = -5800383025104076836L;
	
	private static Logger log = Logger.getLogger("ServiceLocatorException");

	/**
	 * Default constructor
	 */
	public ServiceLocatorException() {
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            exception message
	 */
	public ServiceLocatorException(String msg) {
		super(msg);
		log.log(Level.INFO, msg);
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            exception message
	 * @param cause
	 *            exception cause
	 */
	public ServiceLocatorException(String msg, Throwable cause) {

		super(msg, cause); // JDK 1.3 or above
		log.log(Level.INFO, msg, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            exception cause
	 */
	public ServiceLocatorException(Throwable cause) {

		super(cause); // JDK 1.3 or above
		log.log(Level.INFO, "Cause: " ,  cause);
	}
}