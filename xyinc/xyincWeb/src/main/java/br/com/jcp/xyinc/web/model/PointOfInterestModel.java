package br.com.jcp.xyinc.web.model;

import java.util.Date;
import java.util.List;

import br.com.jcp.xyinc.ejb.dao.PointOfInterestDao;
import br.com.jcp.xyinc.ejb.dao.local.PointOfInterestDaoLocal;
import br.com.jcp.xyinc.ejb.dto.PointOfInterestAndDistanceDto;
import br.com.jcp.xyinc.ejb.entity.PointOfInterest;
import br.com.jcp.xyinc.ejb.utils.FactoryEJB;
import br.com.jcp.xyinc.web.rest.vo.PointOfInterestAndDistanceVo;

/**
 * PointOfInterestModel
 */
public class PointOfInterestModel {
	
	private PointOfInterestDaoLocal pointOfInterestDaoLocal = (PointOfInterestDaoLocal) FactoryEJB.newEJBLocal(PointOfInterestDao.class);
	
	/**
	 * Find all Point of Interest registered.
	 */
	public List<PointOfInterest> findAll(){ 
		List<PointOfInterest> points = pointOfInterestDaoLocal.findAll();
		return points;
	}
	
	/**
	 * Find the Points Of Interest and its distance from coordinates "centerX" and "centerY", respecting a maximum distance "maxDistance".
	 * 
	 * @param centerX - non negative integer
	 * @param centerY - non negative integer
	 * @param maxDistance - non negative integer
	 */
	public List<PointOfInterestAndDistanceDto> findFiltered(int centerX, int centerY, int maxDistance) {
		List<PointOfInterestAndDistanceDto> points = pointOfInterestDaoLocal.findFiltered(centerX, centerY, maxDistance);
		return points;
	}
	
	/**
	 * Register a new Point Of Interest.
	 * 
	 * @param pointVo - new Point Of Interest
	 */
	public PointOfInterest createProduct(PointOfInterestAndDistanceVo pointVo) {
		Date date = new Date();
		PointOfInterest pointOfInterest = new PointOfInterest();
		pointOfInterest.setCreateDate(date);
		pointOfInterest.setName(pointVo.getName());
		pointOfInterest.setPositionX(pointVo.getPositionX());
		pointOfInterest.setPositionY(pointVo.getPositionY());
		pointOfInterestDaoLocal.persist(pointOfInterest);
		return pointOfInterest;
	}
}
