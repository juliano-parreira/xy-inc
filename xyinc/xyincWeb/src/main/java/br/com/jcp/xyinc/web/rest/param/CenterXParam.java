package br.com.jcp.xyinc.web.rest.param;

import javax.ws.rs.WebApplicationException;

import br.com.jcp.xyinc.web.utils.Constants;

/**
 * Param representation for X Coordinate.
 */
public class CenterXParam extends AbstractParam<Integer> {

	private static final long serialVersionUID = 8297998668610783806L;

	public CenterXParam(String param) throws WebApplicationException {
		super(param, Constants.QUERY_PARAM_CENTER_X, false);
	}

	@Override
	protected Integer parse(String param) throws Throwable {
		
		if (param.trim().isEmpty()) {
			throw new Throwable(Constants.MSG_EMPTY);
		}
		else {
			int value = Integer.parseInt(param);
			if (value >= 0) {
				return value;
			}
			else {
				throw new Throwable(Constants.MSG_INVALID_VALUE);
			}
		}
	}
}