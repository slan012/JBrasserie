package org.cnam.jbrasserie.controlers.shop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;
import org.cnam.jbrasserie.views.shop.clients.ClientTab;
import org.cnam.jbrasserie.views.shop.clients.ClientTableModel;

public class ClientControler implements ActionListener{
	
	

	ClientTab clientView;
	ClientDao clientDao = new ClientDaoImplDb();
	Client selectedClient;
	Client editedClient;
	ClientTableModel clientTableModel;
	boolean isNewClient = false;
	List<Client> clients;
		
	public ClientControler(ClientTab view, ClientTableModel clientTableModel) {
		this.clientView = view;
		this.clientTableModel = clientTableModel;
		clients = clientDao.findAll();
		clientTableModel.update(clients);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case ("Valider"): {
			try {
				editedClient = handleEditedClient();
				if (!isNewClient) {
					this.clientDao.update(editedClient);
				} else {
					this.clientDao.add(editedClient);
				}
			} catch (NullPointerException e1){
				System.out.println("Veuillez remplir tous les champs");
			} catch (Exception e2) {
				System.out.println("Veuillez remplir tous les champs");
			}
			
			this.clientView.clearEditPanel();
			editedClient = null;
			isNewClient = false;
			break;
			}
			
		case ("Nouveau"): {
			isNewClient = true;
			this.clientView.clearEditPanel();
			break;
		}	
		
		case ("Supprimer"): {
			if (clientView.getSelectedRow() >= 0) {
				editedClient = handleEditedClient();
				clientDao.delete(editedClient.getId());
				this.clientView.clearEditPanel();
			} else {
				System.out.println("Sélectionnez une ligne");
			}
			break;
		}	
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
		editedClient = null;
		clients = this.clientDao.findAll();
		clientTableModel.update(clients);
		
	}
	
	public Client handleClientRow(int id) {
		selectedClient = clientDao.findById(id);
		return selectedClient;
	}
	
	public Client handleEditedClient() {
		Map<String, String> editedClientRaw = clientView.getEditedClientData();
		Client editedClient = new Client();
		if (!isNewClient) {
			editedClient.setId(Integer.parseInt(editedClientRaw.get("id")));
		}
		editedClient.setFirstName(editedClientRaw.get("firstName"));
		editedClient.setLastName(editedClientRaw.get("lastName"));
		editedClient.setAdress(editedClientRaw.get("adress"));
		editedClient.setZipCode(Integer.parseInt(editedClientRaw.get("zipCode")));
		editedClient.setCity(editedClientRaw.get("city"));
		editedClient.setPhone(Integer.parseInt(editedClientRaw.get("phone")));
		return editedClient;
	}
	
	
}
