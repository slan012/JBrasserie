package org.cnam.jbrasserie.controlers.shop;

import java.util.List;
import java.util.Map;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;
import org.cnam.jbrasserie.tablesModels.ClientTableModel;
import org.cnam.jbrasserie.views.shop.ClientTab;

public class ClientControler {

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

	// Record / Update client button
	public void updateClient() {
		try {
			editedClient = handleEditedClient();
			if (!isNewClient) {
				this.clientDao.update(editedClient);
			} else {
				this.clientDao.add(editedClient);
			}
			this.clientView.clearEditPanel();
			this.clientView.setTextFieldsEditable(false);
			this.clientView.setUpdateButtonState(false);
			this.clientView.setDeleteButtonState(false);
			this.clientView.changeUpdateButtonName(true);
		} catch (NullPointerException e1) {
			System.out.println("Veuillez remplir tous les champs");
		} catch (Exception e2) {
			System.out.println("Veuillez remplir tous les champs");
		}

		editedClient = null;
		isNewClient = false;
		updateTable();
	}

	// New client button
	public void newClient() {
		isNewClient = true;
		this.clientView.clearEditPanel();
		this.clientView.setTextFieldsEditable(true);
		this.clientView.setUpdateButtonState(true);
		this.clientView.setDeleteButtonState(false);
		this.clientView.changeUpdateButtonName(true);
	}
	
	// Delete button
	public void deleteClient() {
		if (clientView.getSelectedRow() >= 0) {
			editedClient = handleEditedClient();
			clientDao.delete(editedClient.getId());
			this.clientView.clearEditPanel();
		} else {
			System.out.println("Sélectionnez une ligne");
		}
		this.clientView.setTextFieldsEditable(false);
		this.clientView.setUpdateButtonState(false);
		this.clientView.setDeleteButtonState(false);
		this.clientView.changeUpdateButtonName(true);
		updateTable();
	}
	
	public void updateTable() {
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
		editedClient = new Client();
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
