/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jcp.xyinc.web.rest.v1.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.jcp.xyinc.ejb.dto.PointOfInterestAndDistanceDto;
import br.com.jcp.xyinc.ejb.entity.PointOfInterest;
import br.com.jcp.xyinc.web.model.PointOfInterestModel;
import br.com.jcp.xyinc.web.rest.exception.CustomWebApplicationException;
import br.com.jcp.xyinc.web.rest.exception.CustomWebApplicationException.ErrorCode;
import br.com.jcp.xyinc.web.rest.param.CenterXParam;
import br.com.jcp.xyinc.web.rest.param.CenterYParam;
import br.com.jcp.xyinc.web.rest.param.MaxDistanceParam;
import br.com.jcp.xyinc.web.rest.vo.PointOfInterestAndDistanceVo;
import br.com.jcp.xyinc.web.utils.Constants;

/**
 * REST Web Service
 */
@Path("v1/points")
public class PointOfInterestResource {

	@Context
	private UriInfo context;

	private PointOfInterestModel pointOfInterestModel = new PointOfInterestModel();

	/**
	 * Creates a new instance of ProductResource
	 */
	public PointOfInterestResource() {
	}
	
	/**
	 * Register a new Point of Interest.
	 * 
	 * Example:
	 * 
	 * Request:
	 *   http://localhost:8080/xyincWeb/rest/v1/points
	 *   POST
	 *   Content-Type: application/json;charset=utf-8
	 *   {"name":"Local","positionX":10,"positionY":20}
	 * 
	 * Response:
	 *   Status 201
	 *   Content-Type: application/json;charset=utf-8
	 *   {"id":10,"name":"Park","positionX":58,"positionY":56}
	 * 
	 * 
	 * @param pPointOfInterestAndDistanceVo - JSON representing the Point of Interest
	 * 
	 */
    @POST
    @Produces(Constants.APPLICATION_JSON_CHARSET_UTF8)
    @Consumes(Constants.APPLICATION_JSON_CHARSET_UTF8)
    public Response createPointOfInterest(
			PointOfInterestAndDistanceVo pPointOfInterestAndDistanceVo
			) {
    	try {
			checkCreatePointOfInterestForNulls(pPointOfInterestAndDistanceVo);
			
			PointOfInterest pointOfInterest = pointOfInterestModel.createProduct(pPointOfInterestAndDistanceVo);
			
			return Response.status(Response.Status.CREATED).entity(getPointOfInterestVo(pointOfInterest)).build();

		} catch(WebApplicationException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new CustomWebApplicationException(ErrorCode.UNKNOWN_ERROR);
		}
	}

    /**
     * List all Points of Interest registered.
	 * 
	 * Example:
	 * 
	 * Request:
	 *   http://localhost:8080/xyincWeb/rest/v1/points
	 *   GET
	 * 
	 * Response:
	 *   Status 200
	 *   Content-Type: application/json;charset=utf-8
	 *   [{"id":1,"name":"Lanchonete","positionX":27,"positionY":12},
	 *   ...,
	 *   {"id":10,"name":"Park","positionX":58,"positionY":56}]
	 *   
     */
	@GET
	@Produces(Constants.APPLICATION_JSON_CHARSET_UTF8)
	public Response getPoinOfInterestList() {
		try {
			
			List<PointOfInterest> pointsList = pointOfInterestModel.findAll();
			
			if ((pointsList == null) || (pointsList.size() == 0)) {
				throw new CustomWebApplicationException(ErrorCode.POINT_LIST_NOT_FOUND, Constants.MSG_POINT_LIST_NOT_FOUND);
			}
			else {
				return Response.status(Response.Status.OK).entity(getListOfPointOfInterest(pointsList)).build();
			}
			
		} catch(WebApplicationException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new CustomWebApplicationException(ErrorCode.UNKNOWN_ERROR);
		}
	}

	/**
	 * List the Points Of Interest and its distance from coordinates "centerX" and "centerY", respecting a maximum distance "maxDistance".
	 * The sort order of closest to farthest.
	 * 
	 * Example:
	 * 
	 * Request:
	 *   http://localhost:8080/xyincWeb/rest/v1/points/filtered?x=20&y=10&d-max=10
	 *   GET
	 * 
	 * Response:
	 *   Status 200
	 *   Content-Type: application/json;charset=utf-8
	 *   [{"id":6,"name":"Supermercado","positionX":23,"positionY":6,"distance":5.0},
	 *   ...,
	 *   {"id":5,"name":"Pub","positionX":12,"positionY":8,"distance":8.2}]
	 *   
	 * 
	 * @param centerX - non negative integer
	 * @param centerY - non negative integer
	 * @param maxDistance - non negative integer
	 * 
	 */
	@GET
	@Path("filtered")
	@Produces(Constants.APPLICATION_JSON_CHARSET_UTF8)
	public Response getPoinOfInterestListFiltered(
			@QueryParam(Constants.QUERY_PARAM_CENTER_X) CenterXParam qCenterX,
			@QueryParam(Constants.QUERY_PARAM_CENTER_Y) CenterYParam qCenterY,
			@QueryParam(Constants.QUERY_PARAM_MAX_DISTANCE) MaxDistanceParam qMaxDistance
			) {
		try {
			
			if ((qCenterX == null) || (qCenterY == null) || (qMaxDistance == null)) {
				throw new CustomWebApplicationException(ErrorCode.PARAM_NOT_FOUND, Constants.MSG_POINT_AND_DISTANCE_LIST_PARAM_NOT_FOUND);
			}
			
			List<PointOfInterestAndDistanceDto> pointsAndDistanceList = pointOfInterestModel.findFiltered(qCenterX.getValue(), qCenterY.getValue(), qMaxDistance.getValue());
			
			if ((pointsAndDistanceList == null) || (pointsAndDistanceList.size() == 0)) {
				throw new CustomWebApplicationException(ErrorCode.POINT_AND_DISTANCE_LIST_NOT_FOUND, String.format(Constants.MSG_POINT_AND_DISTANCE_LIST_NOT_FOUND, qCenterX.getValue(), qCenterY.getValue(), qMaxDistance.getValue()));
			}
			else {
				return Response.status(Response.Status.OK).entity(getLisOfPointOfInterestAndDistance(pointsAndDistanceList)).build();
			}
			
		} catch(WebApplicationException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new CustomWebApplicationException(ErrorCode.UNKNOWN_ERROR);
		}
	}	
	
	private void checkCreatePointOfInterestForNulls(PointOfInterestAndDistanceVo pointOfInterestAndDistanceVo) throws WebApplicationException {
		
		if ((pointOfInterestAndDistanceVo == null) ||
				(pointOfInterestAndDistanceVo.getName() == null) ||
				(pointOfInterestAndDistanceVo.getPositionX() == null) ||
				(pointOfInterestAndDistanceVo.getPositionY() == null) ||
				(pointOfInterestAndDistanceVo.getPositionX() < 0) ||
				(pointOfInterestAndDistanceVo.getPositionX() < 0)
				) {
			throw new CustomWebApplicationException(ErrorCode.PARAM_INVALID, Constants.MSG_POINT_OF_INTEREST_INVALID);
		}
	}
	
	private List<PointOfInterestAndDistanceVo> getLisOfPointOfInterestAndDistance(List<PointOfInterestAndDistanceDto> pointAndDistanceDtoList) {
		
		List<PointOfInterestAndDistanceVo> pointOfInterestAndDistanceVoList = new ArrayList<PointOfInterestAndDistanceVo>();
		
		PointOfInterestAndDistanceVo pointAndDistanceVo;
		for (PointOfInterestAndDistanceDto pointAndDistanceDto : pointAndDistanceDtoList) {
			pointAndDistanceVo = new PointOfInterestAndDistanceVo();
			pointAndDistanceVo.setId(pointAndDistanceDto.getId());
			pointAndDistanceVo.setName(pointAndDistanceDto.getName());
			pointAndDistanceVo.setPositionX(pointAndDistanceDto.getPositionX());
			pointAndDistanceVo.setPositionY(pointAndDistanceDto.getPositionY());
			pointAndDistanceVo.setDistance(pointAndDistanceDto.getDistance());
			pointOfInterestAndDistanceVoList.add(pointAndDistanceVo);
		}
		return pointOfInterestAndDistanceVoList;
	}
	
	private List<PointOfInterestAndDistanceVo> getListOfPointOfInterest(List<PointOfInterest> pointOfInterestList) {
		
		List<PointOfInterestAndDistanceVo> pointOfInterestAndDistanceVoList = new ArrayList<PointOfInterestAndDistanceVo>();
		
		PointOfInterestAndDistanceVo pointAndDistanceVo;
		for (PointOfInterest pointOfInterest : pointOfInterestList) {
			pointAndDistanceVo = new PointOfInterestAndDistanceVo();
			pointAndDistanceVo.setId(pointOfInterest.getId());
			pointAndDistanceVo.setName(pointOfInterest.getName());
			pointAndDistanceVo.setPositionX(pointOfInterest.getPositionX());
			pointAndDistanceVo.setPositionY(pointOfInterest.getPositionY());
			pointOfInterestAndDistanceVoList.add(pointAndDistanceVo);
		}
		return pointOfInterestAndDistanceVoList;
	}
	
	private PointOfInterestAndDistanceVo getPointOfInterestVo(PointOfInterest pointOfInterest) {
		
		PointOfInterestAndDistanceVo pointAndDistanceVo = new PointOfInterestAndDistanceVo();
		pointAndDistanceVo.setId(pointOfInterest.getId());
		pointAndDistanceVo.setName(pointOfInterest.getName());
		pointAndDistanceVo.setPositionX(pointOfInterest.getPositionX());
		pointAndDistanceVo.setPositionY(pointOfInterest.getPositionY());
		
		return pointAndDistanceVo;
	}
}
