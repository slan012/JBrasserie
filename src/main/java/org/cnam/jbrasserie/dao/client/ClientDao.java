package org.cnam.jbrasserie.dao.client;

import java.util.List;

import org.cnam.jbrasserie.beans.Client;

public interface ClientDao {
	public List<Client> findAll();
	public List<Client> findByName(String name);
	public Client findById(int id);
	public void update(Client client);	
	public void delete (int id);
	public void add(Client client);

}
