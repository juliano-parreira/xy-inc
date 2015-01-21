/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jcp.xyinc.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.jcp.xyinc.ejb.dao.local.PointOfInterestDaoLocal;
import br.com.jcp.xyinc.ejb.dto.PointOfInterestAndDistanceDto;
import br.com.jcp.xyinc.ejb.entity.PointOfInterest;

/**
 *
 * @author Juliano Parreira
 */
@Stateless
public class PointOfInterestDao extends AbstractDao<PointOfInterest> implements PointOfInterestDaoLocal {
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PointOfInterestDao() {
		super(PointOfInterest.class);
	}

	private static final String QUERY_POINT_OF_INTEREST_AND_DISTANCE = 
			" SELECT o.id, o.name, o.position_x, o.position_y, o.create_date, ROUND( SQRT( POW( ( o.position_x - ? ) , 2 ) + POW( ( o.position_y - ? ) , 2 ) ), 1) as distance " +
			" FROM point_of_interest o " +
			" WHERE SQRT( POW( ( o.position_x - ? ) , 2 ) + POW( ( o.position_y - ? ) , 2 ) ) <= ? " +
			" ORDER BY distance ASC ";
	
	@Override
	public List<PointOfInterestAndDistanceDto> findFiltered(int centerX, int centerY, int maxDistance) {
		try {
//			Query q = getEntityManager().createQuery(
//					" SELECT new PointOfInterestAndDistanceDto(o, ROUND( SQRT( POW( ( o.positionX - :centerX) , 2 ) + POW( ( o.positionY - :centerY ) , 2 ) ), 1) as distance) " +
//							" FROM PointOfInterest o " +
//							" WHERE SQRT( POW( ( o.positionX - :centerX) , 2 ) + POW( ( o.positionY - :centerY ) , 2 ) ) <= :maxDistance " +
//							" ORDER BY distance "
//					);
			Query q = getEntityManager().createNativeQuery(QUERY_POINT_OF_INTEREST_AND_DISTANCE, PointOfInterestAndDistanceDto.class);
			
			q.setParameter("1", centerX);
			q.setParameter("2", centerY);
			q.setParameter("3", centerX);
			q.setParameter("4", centerY);
			q.setParameter("5", maxDistance);
			
			@SuppressWarnings("unchecked")
			List<PointOfInterestAndDistanceDto> list = q.getResultList();
			System.out.println("PointOfInterestDAO findFiltered list: " + list);
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
