package org.cnam.jbrasserie.dao.beer;

import java.util.List;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.exceptions.DaoException;

public interface BeerDao {
	public List<Beer> findAll() ;
	public Beer findById(int id);
	public List<Beer> findByName(String name);
	public void add(Beer beer);
	public void delete(int id) throws DaoException;
	public void update(Beer beer);
	List<Beer> findAllWithStock();
}
