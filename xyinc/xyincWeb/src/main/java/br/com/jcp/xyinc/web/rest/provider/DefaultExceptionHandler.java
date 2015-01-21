package br.com.jcp.xyinc.web.rest.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.jcp.xyinc.web.rest.vo.ErrorVo;
import br.com.jcp.xyinc.web.utils.Constants;

import com.sun.jersey.api.client.ClientResponse.Status;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<WebApplicationException> {
	
	public Response toResponse(WebApplicationException ex) {
		
		ErrorVo  errorVo = new ErrorVo();
		errorVo.setErrorCode(ex.getResponse().getStatus());
		errorVo.setMessage(getFormatedMessage(ex));
		
		return Response.status(ex.getResponse().getStatus()).
				entity(errorVo).
				type(Constants.APPLICATION_JSON_CHARSET_UTF8).
				build();
	}
	
	private String getFormatedMessage(WebApplicationException ex) {
		
		Status status = Status.fromStatusCode(ex.getResponse().getStatus());
		if (status == null) {
			status = Status.INTERNAL_SERVER_ERROR;
		}
		
		if (ex.getMessage() == null) {
			return status.name();
		}
		else {
			return status.name() + ": " + ex.getMessage();
		}
	}
}
