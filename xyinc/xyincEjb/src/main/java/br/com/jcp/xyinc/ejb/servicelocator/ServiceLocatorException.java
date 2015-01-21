package br.com.jcp.xyinc.ejb.servicelocator;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Implementação do Design Pattern Service Locator.
 */
public class ServiceLocatorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5800383025104076836L;
	/**
	 * 
	 */
	private static Logger log = Logger.getLogger("ServiceLocatorException");

	/**
	 * Constructor padrão de Exceção do Service Locator
	 */
	public ServiceLocatorException() {
	}

	/**
	 * Constructor de Exceção do Service Locator
	 * 
	 * @param msg
	 *            mensagem de exceção
	 */
	public ServiceLocatorException(String msg) {
		super(msg);
		log.log(Level.INFO, msg);
	}

	/**
	 * Constructor de Exceção do Service Locator
	 * 
	 * @param msg
	 *            mensagem de exceção
	 * @param cause
	 *            causa
	 */
	public ServiceLocatorException(String msg, Throwable cause) {

		super(msg, cause); // JDK 1.3 ou superior
		log.log(Level.INFO, msg, cause);
	}

	/**
	 * Constructor de Exceção do Service Locator
	 * 
	 * @param cause
	 *            causa
	 */
	public ServiceLocatorException(Throwable cause) {

		super(cause); // JDK 1.3 ou superior
		log.log(Level.INFO, "Cause: " ,  cause);
	}
}