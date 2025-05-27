package org.cnam.jbrasserie.dao;

import java.util.List;

import org.cnam.jbrasserie.beans.Beer;

public interface BeerDao {
	public List<Beer> findAll() ;
	public Beer findById(int id);
	public List<Beer> findByName(String name);
	public void insertBeer(Beer beer);
	public void deleteBeer(int id);
	public void updateBeer(int id, Beer beer);
}
