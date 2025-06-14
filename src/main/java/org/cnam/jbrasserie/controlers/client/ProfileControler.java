package org.cnam.jbrasserie.controlers.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.views.client.ClientView;
import org.cnam.jbrasserie.views.client.ProfileTab;

public class ProfileControler implements ActionListener {

	ProfileTab profileView;
	ClientView clientView;
	ClientDao clientDao;

	public ProfileControler(ProfileTab profileView, ClientView clientView) {
		this.profileView = profileView;
		this.clientView = clientView;
		this.clientDao = new ClientDaoImplDb();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ("Se connecter"):
			int id = profileView.getConnexionId();
			System.out.println("Connexion avec l'identifiant : " + id);
			if (id != 0) {
					Client client = clientDao.findById(id);
				System.out.println(client.getLastName());
				if (client.getId() != null) {
					if (Session.getCurrentUser() == null || !Session.getCurrentUser().getId().equals(client.getId())) {
						Session.setCurrentUser(client);
						Session.setCurrentOrder(new Order());
						
					}
					clientView.newTabs();
					this.profileView.update(client);
					this.profileView.changeFieldEditableState(true);
					System.out.print(Session.getCurrentOrder());
				}
			} else {
				this.profileView.clearClientField();
				this.profileView.changeFieldEditableState(false);
				System.out.print("Identifiant invalide");

			}
			System.out.println("Connexion");
			break;
		case ("Modifier"):
			System.out.println("Modifier");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
	}
}
