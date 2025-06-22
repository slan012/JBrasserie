package org.cnam.jbrasserie.controlers.client;

import java.util.Map;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.dao.FactoryDao;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.exceptions.BeanException;
import org.cnam.jbrasserie.exceptions.FormException;
import org.cnam.jbrasserie.observers.ClientNotifier;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.views.client.ClientView;
import org.cnam.jbrasserie.views.client.ProfileTab;

public class ProfileControler {

	private ProfileTab profileView;
	private ClientView clientView;
	private ClientDao clientDao;
	private Client editedClient;

	public ProfileControler(ProfileTab profileView, ClientView clientView) {
		this.profileView = profileView;
		this.clientView = clientView;
		this.clientDao = FactoryDao.getClientDao();
	}
	
	public void updateUser(){
		try {
			this.profileView.clearMessage();
			editedClient = handleEditedClient();
			clientDao.update(editedClient);
			ClientNotifier.clientUpdated();
			profileView.showSuccess("Informations mises à jour");
		} catch (FormException e) {
			this.profileView.showError(e.getMessage());
		} catch (BeanException e) {
			profileView.showError(e.getMessage());
		}
	}
	
	public void connectUser() {
		try {
			int id = Integer.parseInt(profileView.getConnexionId());
			Client client = clientDao.findById(id);
			if (client.getId() != null) {
				this.profileView.clearMessage();
				if (Session.getCurrentUser() == null || !Session.getCurrentUser().getId().equals(client.getId())) {
					Session.setCurrentUser(client);
					Session.setCurrentOrder(new Order());
				}
				
				clientView.newTabs();
				this.profileView.update(client);
				this.profileView.setEditPanelEditable(true);
				this.profileView.showSuccess("Bienvenue " + Session.getCurrentUser().getFirstName() + " !");
			} else {
				this.resetViews();
				this.profileView.showError("Cet utilisateur n'existe pas");
			}
		} catch (NumberFormatException e) {
			this.resetViews();
			this.profileView.showError("L'identifiant doit être un nombre");
		}
	}
	
	public Client handleEditedClient() throws FormException, BeanException {
		Map<String, String> editedClientRaw = profileView.getEditedClientData();
		for (Map.Entry<String, String> field : editedClientRaw.entrySet()) {
			if (!field.getKey().equals("id") && field.getValue().isBlank()) {
					throw new FormException("Tous les champs doivent être remplis");
				}
		}
		editedClient = new Client();
		editedClient.setId(Integer.parseInt(editedClientRaw.get("id")));
		editedClient.setFirstName(editedClientRaw.get("firstName"));
		editedClient.setLastName(editedClientRaw.get("lastName"));
		editedClient.setAdress(editedClientRaw.get("adress"));
		try {
			editedClient.setZipCode(Integer.parseInt(editedClientRaw.get("zipCode")));
		} catch (NumberFormatException e) {
			throw new FormException("Le code postal doit être un nombre");
		}
		editedClient.setCity(editedClientRaw.get("city"));
		editedClient.setPhone(editedClientRaw.get("phone"));

		return editedClient;
	}
	
	private void resetViews() {
		this.profileView.clearClientField();
		this.profileView.setEditPanelEditable(false);
		this.clientView.removeTabs();
	}
}
