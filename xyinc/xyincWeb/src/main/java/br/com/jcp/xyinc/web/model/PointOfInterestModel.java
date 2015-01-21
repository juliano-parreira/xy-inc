package br.com.jcp.xyinc.web.model;

import java.util.Date;
import java.util.List;

import br.com.jcp.xyinc.ejb.dao.PointOfInterestDao;
import br.com.jcp.xyinc.ejb.dao.local.PointOfInterestDaoLocal;
import br.com.jcp.xyinc.ejb.dto.PointOfInterestAndDistanceDto;
import br.com.jcp.xyinc.ejb.entity.PointOfInterest;
import br.com.jcp.xyinc.ejb.utils.FactoryEJB;
import br.com.jcp.xyinc.web.rest.vo.PointOfInterestAndDistanceVo;

public class PointOfInterestModel {
	
	private PointOfInterestDaoLocal pointOfInterestDaoLocal = (PointOfInterestDaoLocal) FactoryEJB.newEJBLocal(PointOfInterestDao.class);
	
	public List<PointOfInterest> findAll(){ 
		List<PointOfInterest> points = pointOfInterestDaoLocal.findAll();
		return points;
	}
	
	public List<PointOfInterestAndDistanceDto> findFiltered(int centerX, int centerY, int maxDistance) {
		List<PointOfInterestAndDistanceDto> points = pointOfInterestDaoLocal.findFiltered(centerX, centerY, maxDistance);
		return points;
	}
	
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
