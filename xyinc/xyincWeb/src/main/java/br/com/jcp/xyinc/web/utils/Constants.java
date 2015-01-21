package br.com.jcp.xyinc.web.utils;


public interface Constants {
	
	/*
	 * REST Services
	 */
	public static final String APPLICATION_JSON_CHARSET_UTF8 = "application/json; charset=utf-8";
	public static final String UTF8 = "UTF-8";
	
	/*
	 * Parameters
	 */
	public static final String QUERY_PARAM_CENTER_X = "x";
	public static final String QUERY_PARAM_CENTER_Y = "y";
	public static final String QUERY_PARAM_MAX_DISTANCE = "d-max";
	
	/*
	 * Messages
	 */
	public static final String MSG_EMPTY = "Empty value";
	public static final String MSG_INVALID_VALUE = "Invalid value";
	public static final String MSG_INVALID_JSON = "Invalid JSON: ";
	
	public static final String MSG_POINT_OF_INTEREST_INVALID = "Point of interest object is invalid";
	
	public static final String MSG_POINT_AND_DISTANCE_LIST_PARAM_NOT_FOUND = "All parameters are required ("
			+ QUERY_PARAM_CENTER_X + ", " + QUERY_PARAM_CENTER_Y + " and " + QUERY_PARAM_MAX_DISTANCE + ").";
	
	public static final String MSG_POINT_LIST_NOT_FOUND = "No points of interest found";
	public static final String MSG_POINT_AND_DISTANCE_LIST_NOT_FOUND = "No points of interest found for given parameters ("
			+ QUERY_PARAM_CENTER_X + ": %d, " + QUERY_PARAM_CENTER_Y + ": %d, " + QUERY_PARAM_MAX_DISTANCE + ": %d)";
}
