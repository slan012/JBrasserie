package org.cnam.jbrasserie.views.shop.clients;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cnam.jbrasserie.beans.Client;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ClientEditPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField firstNameField;
	JTextField lastNameField;
	JTextField adressField;
	JTextField zipCodeField;
	JTextField cityField;
	JTextField phoneField;

	/**
	 * Create the panel.
	 */
	public ClientEditPanel() {

		// Labels
		JLabel firstNameLabel =   new JLabel("Prénom :");
		JLabel lastNameLabel = new JLabel("Nom :");
		JLabel adressLabel =  new JLabel("Adresse :");
		JLabel zipCodeLabel = new JLabel("Code postal :");
		JLabel cityLabel =  new JLabel("Ville :");
		JLabel phoneLabel =  new JLabel("Numéro de téléphone :");
		
		// Fields		
		 firstNameField = new JTextField("Test");
		 
		 lastNameField = new JTextField();
		 adressField =  new JTextField();
		 zipCodeField = new JTextField();
		 cityField =  new JTextField();
		 phoneField =  new JTextField();
		
		// Buttons
		JButton submitButton = new JButton("Valider");
		JButton deleteButton = new JButton("Supprimer");
		JButton newButton =    new JButton("Nouveau client");
		

		GroupLayout gl = new GroupLayout(this);
		gl.setHorizontalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(firstNameLabel)
						.addComponent(lastNameLabel)
						.addComponent(adressLabel)
						.addComponent(zipCodeLabel)
						.addComponent(cityLabel)
						.addComponent(phoneLabel))
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(adressField, 366, 366, 366)
						.addComponent(lastNameField, 366, 366, 366)
						.addComponent(firstNameField, 366, 366, 366)
						.addComponent(cityField, 366, 366, 366)
						.addComponent(zipCodeField, 366, 366, 366)
						.addComponent(phoneField, 366, 366, 366)
						.addGroup(gl.createSequentialGroup()
							.addComponent(submitButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteButton)
							.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
							.addComponent(newButton)
							.addContainerGap())))
		);
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstNameLabel)
						.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(lastNameLabel)
						.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(adressLabel)
						.addComponent(adressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(zipCodeLabel)
						.addComponent(zipCodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(cityLabel)
						.addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(phoneLabel)
						.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(newButton)
						.addComponent(submitButton)
						.addComponent(deleteButton)))
		);
		
		this.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		System.out.print(firstNameField.hashCode());
	}
	
	public JTextField getFirstNameField() {
		return firstNameField;
	}
	
	public void update(Client client){
		System.out.println(client.getLastName());
		System.out.print(firstNameField.hashCode());
	}
}
