package br.com.jcp.xyinc.ejb.servicelocator;


import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBHome;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

/**
 *  This class is an implementation of the Service Locator pattern. It is 
 *  used to looukup resources such as EJBHomes, JMS Destinations, etc. 
 */
public class ServiceLocator {

	private InitialContext ic;

	private static ServiceLocator me;

	@SuppressWarnings("rawtypes")
	Map cache;

	static {
		try {
			me = new ServiceLocator();
		} catch(RuntimeException se) {
			System.err.println(se);
			se.printStackTrace(System.err);
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ServiceLocator() throws ServiceLocatorException  {
		try {
			ic = new InitialContext();
			cache = Collections.synchronizedMap(new HashMap());

		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
	}
	public static ServiceLocator getInstance() {
		return me;
	}


	/**
	 * will get the ejb Local home factory. 
	 * clients need to cast to the type of EJBHome they desire
	 *
	 * @return the Local EJB Home corresponding to the homeName
	 */
	@SuppressWarnings("unchecked")
	public Object getLocalHome(String jndiHomeName)
			throws ServiceLocatorException {
		Object home = null;
		try { 
			if (cache.containsKey(jndiHomeName)) {
				home =  cache.get(jndiHomeName);
			} else {         
				home = ic.lookup(jndiHomeName);
				cache.put(jndiHomeName, home);
			}
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return home;
	}


	/**
	 * will get the ejb Remote home factory. 
	 * clients need to cast to the type of EJBHome they desire
	 *
	 * @return the EJB Home corresponding to the homeName
	 */
	public EJBHome getRemoteHome(String jndiHomeName, @SuppressWarnings("rawtypes") Class className) throws ServiceLocatorException {
		EJBHome home = null;
		try { 
			Object objref = ic.lookup(jndiHomeName);
			Object obj = PortableRemoteObject.narrow(objref, className);
			home = (EJBHome)obj;
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return home;
	}


	/**
	 * @return the factory for the factory to get queue connections from
	 */
	public  QueueConnectionFactory getQueueConnectionFactory(String qConnFactoryName) 
			throws ServiceLocatorException {
		QueueConnectionFactory factory = null;
		try {
			factory = (QueueConnectionFactory) ic.lookup(qConnFactoryName);
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return factory;
	}


	/**
	 * @return the Queue Destination to send messages to
	 */
	public  Queue getQueue(String queueName) throws ServiceLocatorException {
		Queue queue = null;
		try {
			queue =(Queue)ic.lookup(queueName);
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return queue;
	}

	/**
	 * This method helps in obtaining the topic factory
	 * @return the factory for the factory to get topic connections from
	 */
	public  TopicConnectionFactory getTopicConnectionFactory(String topicConnFactoryName) throws ServiceLocatorException {
		TopicConnectionFactory factory = null;
		try {
			factory = (TopicConnectionFactory) ic.lookup(topicConnFactoryName);
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return factory;
	}

	/**
	 * This method obtains the topc itself for a caller
	 * @return the Topic Destination to send messages to
	 */
	public  Topic getTopic(String topicName) throws ServiceLocatorException {
		Topic topic = null;
		try {
			topic = (Topic)ic.lookup(topicName);
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return topic;
	}

	/**
	 * This method obtains the datasource itself for a caller
	 * @return the DataSource corresponding to the name parameter
	 */
	public DataSource getDataSource(String dataSourceName) throws ServiceLocatorException {
		DataSource dataSource = null;
		try {
			dataSource = (DataSource)ic.lookup(dataSourceName);
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return dataSource;
	}

	/**
	 * @return the URL value corresponding
	 * to the env entry name.
	 */
	public URL getUrl(String envName) throws ServiceLocatorException {  
		URL url = null;
		try {
			url = (URL)ic.lookup(envName);   
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}

		return url;         
	}

	/**
	 * @return the boolean value corresponding
	 * to the env entry such as SEND_CONFIRMATION_MAIL property.
	 */
	public boolean getBoolean(String envName) throws ServiceLocatorException {   
		Boolean bool = null;
		try {    
			bool = (Boolean)ic.lookup(envName);          
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return bool.booleanValue();
	}

	/**
	 * @return the String value corresponding
	 * to the env entry name.
	 */
	public String getString(String envName) throws ServiceLocatorException { 
		String envEntry = null;   
		try {  
			envEntry = (String)ic.lookup(envName);      
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		} catch (ServiceLocatorException e) {
			throw new ServiceLocatorException(e);
		}
		return envEntry ;
	}

}
