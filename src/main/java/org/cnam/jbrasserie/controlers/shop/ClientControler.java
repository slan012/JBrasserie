package org.cnam.jbrasserie.controlers.shop;

import java.util.List;
import java.util.Map;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.dao.FactoryDao;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.exceptions.BeanException;
import org.cnam.jbrasserie.exceptions.DaoException;
import org.cnam.jbrasserie.exceptions.FormException;
import org.cnam.jbrasserie.tablesModels.ClientTableModel;
import org.cnam.jbrasserie.views.shop.ClientTab;

public class ClientControler {

	ClientTab clientView;
	ClientDao clientDao = FactoryDao.getClientDao();
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
		this.clientView.clearMessage();
		try {
			editedClient = handleEditedClient();
			if (!isNewClient) {
				this.clientDao.update(editedClient);
				this.clientView.showSuccess("Informations mises à jour");
			} else {
				this.clientDao.add(editedClient);
				this.clientView.showSuccess("Nouveau client enregistré");
			}
			editedClient = null;
			isNewClient = false;
			updateTable();
			this.clientView.clearEditPanel();
			this.clientView.setTextFieldsEditable(false);
			this.clientView.setUpdateButtonState(false);
			this.clientView.setDeleteButtonState(false);
			this.clientView.changeUpdateButtonName(true);
			
		} catch (FormException e) {
			this.clientView.showError(e.getMessage());
		
		} catch (BeanException e) {
			this.clientView.showError(e.getMessage());
		}
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
		isNewClient = false;
		try {
			if (clientView.getSelectedRow() >= 0) {
				editedClient = handleEditedClient();
				clientDao.delete(editedClient.getId());
				this.clientView.clearEditPanel();
			} 
			this.clientView.setTextFieldsEditable(false);
			this.clientView.setUpdateButtonState(false);
			this.clientView.setDeleteButtonState(false);
			this.clientView.changeUpdateButtonName(true);
			updateTable();
		} catch (DaoException | FormException | BeanException e) {
			this.clientView.showError("Impossible de supprimer un client qui a effectué une commande");
		}
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

	public Client handleEditedClient() throws FormException, BeanException {
		Map<String, String> editedClientRaw = clientView.getEditedClientData();
		for (Map.Entry<String, String> field : editedClientRaw.entrySet()) {
			if (!field.getKey().equals("id") && field.getValue().isBlank()) {
					throw new FormException("Tous les champs doivent être remplis");
				}
		}
		editedClient = new Client();
		if (!isNewClient) {
			editedClient.setId(Integer.parseInt(editedClientRaw.get("id")));
		}
		editedClient.setFirstName(editedClientRaw.get("firstName"));
		editedClient.setLastName(editedClientRaw.get("lastName"));
		editedClient.setAdress(editedClientRaw.get("adress"));
		try {
			editedClient.setZipCode(Integer.parseInt(editedClientRaw.get("zipCode")));
		} catch (NumberFormatException e) {
			throw new FormException("Le code postal doit être un nombre");
		}
		editedClient.setCity(editedClientRaw.get("city"));
		try {
			editedClient.setPhone(editedClientRaw.get("phone"));
		} catch (Exception e) {
			throw new BeanException(e.getMessage());
		}
		return editedClient;
	}
}
