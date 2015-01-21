package br.com.jcp.xyinc.ejb.dao.local;

import java.util.List;

import javax.ejb.Local;

import br.com.jcp.xyinc.ejb.dto.PointOfInterestAndDistanceDto;
import br.com.jcp.xyinc.ejb.entity.PointOfInterest;

@Local
public interface PointOfInterestDaoLocal extends AbstractDaoLocal<PointOfInterest> {
	
	/**
	 * 
	 * @param centerX
	 * @param centerY
	 * @param maxDistance
	 * @return
	 */
    List<PointOfInterestAndDistanceDto> findFiltered(int centerX, int centerY, int maxDistance);
}
