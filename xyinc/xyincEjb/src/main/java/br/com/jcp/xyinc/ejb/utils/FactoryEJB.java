package br.com.jcp.xyinc.ejb.utils;

import br.com.jcp.xyinc.ejb.servicelocator.ServiceLocator;

/**
 * EJB Factory
 */
public class FactoryEJB {
	
	public static Object newEJBLocal(Class<?> daoClass) {
		return ServiceLocator.getInstance().getLocalHome(Constants.JNDI_NAME_GLOBAL+daoClass.getSimpleName());
	}
}
