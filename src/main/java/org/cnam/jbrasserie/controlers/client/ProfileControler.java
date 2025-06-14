package org.cnam.jbrasserie.controlers.client;

import java.util.Map;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.views.client.ClientView;
import org.cnam.jbrasserie.views.client.ProfileTab;

public class ProfileControler {

	ProfileTab profileView;
	ClientView clientView;
	ClientDao clientDao;
	Client editedClient;

	public ProfileControler(ProfileTab profileView, ClientView clientView) {
		this.profileView = profileView;
		this.clientView = clientView;
		this.clientDao = new ClientDaoImplDb();
	}
	
	public void updateUser() {
		editedClient = handleEditedClient();
		clientDao.update(editedClient);
	}
	
	public void connectUser() {
		try {
			int id = Integer.parseInt(profileView.getConnexionId());
			System.out.println(id);
			Client client = clientDao.findById(id);
			if (client.getId() != null) {
				System.out.println(client.getLastName());
				
				if (Session.getCurrentUser() == null || !Session.getCurrentUser().getId().equals(client.getId())) {
					System.out.println("Connexion avec l'identifiant : " + id);
					Session.setCurrentUser(client);
					Session.setCurrentOrder(new Order());
				}
				
				clientView.newTabs();
				this.profileView.update(client);
				this.profileView.setEditPanelEditable(true);
				System.out.print(Session.getCurrentOrder());
				
			} else {
					this.resetViews();
					System.out.print("Ce compte n'existe pas");
			}
				
		} catch (NumberFormatException e2) {
			this.resetViews();
			System.out.println("Identifiant invalide");
		}
	}
	
	public Client handleEditedClient() {
		Map<String, String> editedClientRaw = profileView.getEditedClientData();
		editedClient = new Client();
		editedClient.setId(Integer.parseInt(editedClientRaw.get("id")));
		editedClient.setFirstName(editedClientRaw.get("firstName"));
		editedClient.setLastName(editedClientRaw.get("lastName"));
		editedClient.setAdress(editedClientRaw.get("adress"));
		editedClient.setZipCode(Integer.parseInt(editedClientRaw.get("zipCode")));
		editedClient.setCity(editedClientRaw.get("city"));
		editedClient.setPhone(Integer.parseInt(editedClientRaw.get("phone")));
		return editedClient;
	}
	
	private void resetViews() {
		this.profileView.clearClientField();
		this.profileView.setEditPanelEditable(false);
		this.clientView.removeTabs();
	}

	
}
