package org.cnam.jbrasserie.controlers.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;
import org.cnam.jbrasserie.session.Session;
import org.cnam.jbrasserie.views.client.profile.ProfileTab;

public class ProfileControler implements ActionListener {
	
	ProfileTab view;
	ClientDao clientDao;
	Client client;
	ClientCatalogControler clientCatalogControler;
	
	public ProfileControler(ProfileTab view) {
		this.view = view;
		this.clientDao = new ClientDaoImplDb();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ("Se connecter"):
			int id = view.getConnexionId();
			System.out.println("Connexion avec l'identifiant : " + id);
			if (id != 0) {
				Client client = clientDao.findById(id);
				if (client.getId() != null) {
					Session.setCurrentUser(client);
					Session.setCurrentOrder(null);
					this.view.update(client);
					this.view.changeFieldEditableState(true);
				}
			} else {
				this.view.clearClientField();
				this.view.changeFieldEditableState(false);
				System.out.print("Identifiant invalide");
				
			}
			System.out.println("Connexion");
			break;
		case ("Modifier") :
			System.out.println("Modifier");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}

	}

}
