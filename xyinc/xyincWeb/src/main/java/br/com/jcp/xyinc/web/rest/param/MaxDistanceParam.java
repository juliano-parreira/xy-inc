package br.com.jcp.xyinc.web.rest.param;

import javax.ws.rs.WebApplicationException;

import br.com.jcp.xyinc.web.utils.Constants;

public class MaxDistanceParam extends AbstractParam<Integer> {

	private static final long serialVersionUID = -5832560275459664948L;

	public MaxDistanceParam(String param) throws WebApplicationException {
		super(param, Constants.QUERY_PARAM_MAX_DISTANCE, false);
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