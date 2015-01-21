package br.com.jcp.xyinc.ejb.dao.local;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AbstractDaoLocal<T> {
	
	void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int[] range);

    long count();
}
