package br.com.jcp.xyinc.web.rest.param;

import java.io.Serializable;

import javax.ws.rs.WebApplicationException;

import br.com.jcp.xyinc.web.rest.exception.CustomWebApplicationException;
import br.com.jcp.xyinc.web.rest.exception.CustomWebApplicationException.ErrorCode;

/**
 * Abstract param representation.
 */
public abstract class AbstractParam<V> implements Serializable {
	
	private static final long serialVersionUID = -2974153580333267105L;
	
	private final V value;
	private final String originalParam;

	public AbstractParam(String param, String paramName, boolean isHeaderParam) throws WebApplicationException {
		this.originalParam = param;
		
		try {
			this.value = parse(param);
		} catch (Throwable e) {
			throw newCustomWebApplicationException(paramName, param, isHeaderParam, e);
		}
	}

	public V getValue() {
		return value;
	}

	public String getOriginalParam() {
		return originalParam;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	protected abstract V parse(String param) throws Throwable;

	protected CustomWebApplicationException newCustomWebApplicationException(String paramName, String param, boolean isHeaderParam, Throwable e) {
		if (isHeaderParam) {
			return new CustomWebApplicationException(ErrorCode.HEADER_INVALID, "Invalid header \"" + paramName + "\": \"" + param + "\" (" + e.getMessage() + ")");
		}
		else {
			return new CustomWebApplicationException(ErrorCode.PARAM_INVALID, "Invalid parameter \"" + paramName + "\": \"" + param + "\" (" + e.getMessage() + ")");
		}
	}
}