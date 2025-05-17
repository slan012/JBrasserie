package org.cnam.jbrasserie.dao;

import java.util.List;

import org.cnam.jbrasserie.beans.Client;

public interface ClientDao {
	public List<Client> findAll();
	public Client findByName(String name);
	public void createNew(Client client);
	public void update(int id, Client client);
	public void delete (int id);	
}
