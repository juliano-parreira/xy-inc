package br.com.jcp.xyinc.web.rest.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.StatusType;

import br.com.jcp.xyinc.web.rest.vo.ErrorVo;
import br.com.jcp.xyinc.web.utils.Constants;

import com.sun.jersey.api.client.ClientResponse.Status;

/**
 * Custom exception with a default response representation as JSON.
 */
public class CustomWebApplicationException extends WebApplicationException {
	
	private static final long serialVersionUID = -6408226642049205640L;
	
	private Response response;
	private ErrorCode errorCode;
	private String message;
	
	public CustomWebApplicationException(ErrorCode errorCode) {
		this.response = getResponse(errorCode, errorCode.getName());
		this.errorCode = errorCode;
		this.message = errorCode.getName() + "(code: "+ errorCode.code + " - status: " + errorCode.statusType.getStatusCode() + ")";
	}
	
	public CustomWebApplicationException(ErrorCode errorCode, String overrideMessage) {
		this.response = getResponse(errorCode, overrideMessage);
		this.errorCode = errorCode;
		this.message = overrideMessage + "(code: "+ errorCode.code + " - status: " + errorCode.statusType.getStatusCode() + ")";
	}
	
	@Override
	public Response getResponse() {
		return response;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	private Response getResponse(ErrorCode errorCode, String overrideMessage) {
		ErrorVo  errorVo = new ErrorVo();
		errorVo.setErrorCode(errorCode.getCode());
		errorVo.setMessage(overrideMessage);
		ResponseBuilder builder = Response.status(errorCode.getStatusType())
				.entity(errorVo)
				.type(Constants.APPLICATION_JSON_CHARSET_UTF8);
		return builder.build();
	}
	
	/*
	 * Internal error codes.
	 * Use 4 digit codes to avoid conflict with HTTP_STATUS.
	 */
	public enum ErrorCode {
		
		UNKNOWN_ERROR 				(1000, "SERVER ERROR", Status.INTERNAL_SERVER_ERROR), 
		HEADER_NOT_FOUND 			(1001, "HEADER NOT FOUND", Status.BAD_REQUEST), 
		HEADER_INVALID 				(1002, "HEADER INVALID", Status.BAD_REQUEST),
		PARAM_NOT_FOUND 			(1003, "PARAM NOT FOUND", Status.BAD_REQUEST), 
		PARAM_INVALID 				(1004, "PARAM INVALID", Status.BAD_REQUEST),
		PARAM_FORBIDDEN 			(1005, "PARAM FORBIDDEN", Status.FORBIDDEN),
		
		POINT_LIST_NOT_FOUND		(1101, "PRODUCT LIST NOT FOUND", Status.NOT_FOUND), 
		POINT_AND_DISTANCE_LIST_NOT_FOUND		(1102, "PRODUCT AND DISTANCE LIST NOT FOUND", Status.NOT_FOUND), 
		;
		
		private int code;
		private String name;
		private StatusType statusType;
		
		private ErrorCode(int code,  String name, StatusType statusType) {
			this.code = code;
			this.name = name;
			this.statusType = statusType;
		}
		
		public StatusType getStatusType() {
			return statusType;
		}

		public int getCode() {
			return code;
		}
		
		public String getName() {
			return name;
		}
		
        private static final Map<Integer, ErrorCode> BY_VALUE_MAP = new LinkedHashMap<Integer, ErrorCode>();
        static {
            for (ErrorCode object : ErrorCode.values()) {
                BY_VALUE_MAP.put(object.code, object);
            }
        }
        
        public static ErrorCode forCode(int code) {
        	ErrorCode object = BY_VALUE_MAP.get(code);
        	if (object == null) {
        		object = ErrorCode.UNKNOWN_ERROR;
        	}
            return object;
        }
	}
}