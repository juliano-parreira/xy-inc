package br.com.jcp.xyinc.ejb.dao.local;

import java.util.List;

import javax.ejb.Local;

import br.com.jcp.xyinc.ejb.dto.PointOfInterestAndDistanceDto;
import br.com.jcp.xyinc.ejb.entity.PointOfInterest;

/**
 * DAO declaration for Point of Interest.
 */
@Local
public interface PointOfInterestDaoLocal extends AbstractDaoLocal<PointOfInterest> {
	
	/**
	 * Find the Points Of Interest and its distance from coordinates "centerX" and "centerY", respecting a maximum distance "maxDistance".
	 * 
	 * @param centerX - non negative integer
	 * @param centerY - non negative integer
	 * @param maxDistance - non negative integer
	 */
    List<PointOfInterestAndDistanceDto> findFiltered(int centerX, int centerY, int maxDistance);
}
