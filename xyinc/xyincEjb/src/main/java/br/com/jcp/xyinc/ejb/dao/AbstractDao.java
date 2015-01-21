/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jcp.xyinc.ejb.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal;


public abstract class AbstractDao<T> implements AbstractDaoLocal<T>  {
    private Class<T> entityClass;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    
    /*
     * (non-Javadoc)
     * @see br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal#persist(java.lang.Object)
     */
	public void persist(T entity) {
		try {
			Date now = new Date();
			entity.getClass().getMethod("setCreationDate", Date.class).invoke(entity, now);
			entity.getClass().getMethod("setUpdateDate", Date.class).invoke(entity, now);
		} catch (Exception e) {
		}
        getEntityManager().persist(entity);
    }
	
	/*
	 * (non-Javadoc)
	 * @see br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal#merge(java.lang.Object)
	 */
	public void merge(T entity) {
		try {
			entity.getClass().getMethod("setUpdateDate", Date.class).invoke(entity, new Date());
			System.out.println(entity);
		} catch (Exception e) {
		}
        getEntityManager().merge(entity);
    }
	
	/*
	 * (non-Javadoc)
	 * @see br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal#remove(java.lang.Object)
	 */
	public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
	
	/*
	 * (non-Javadoc)
	 * @see br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal#find(java.lang.Object)
	 */
	public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
	
	/*
	 * (non-Javadoc)
	 * @see br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal#findAll()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
	
	/*
	 * (non-Javadoc)
	 * @see br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal#findRange(int[])
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
	
	/*
	 * (non-Javadoc)
	 * @see br.com.jcp.xyinc.ejb.dao.local.AbstractDaoLocal#count()
	 */
	public long count() {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return (Long) q.getSingleResult();
    }
    
}
