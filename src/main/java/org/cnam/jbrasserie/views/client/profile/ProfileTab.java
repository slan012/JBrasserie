package org.cnam.jbrasserie.views.client.profile;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.controlers.client.ProfileControler;

public class ProfileTab extends JPanel{
	
	private JTextField idField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField adressField;
	private JTextField zipCodeField;
	private JTextField cityField;
	private JTextField phoneField;
	
	List<JTextField> fieldList;
	
	private JTextField loginField;
	
	ProfileControler profileControler;
	
	public ProfileTab() {
		
		profileControler = new ProfileControler(this);
		
		// Login panel
		
		JPanel loginPanel = new JPanel();
		
		JLabel login = new JLabel("Numéro client :");
		loginField = new JTextField();
		loginField.setColumns(20);
		JButton connectButton = new JButton("Se connecter");
		connectButton.addActionListener(this.profileControler);
		
		loginPanel.add(login);
		loginPanel.add(loginField);
		loginPanel.add(connectButton);
		
		// Edit profile panel
		
		JPanel editProfilePanel = new JPanel();
		
		GroupLayout gl = new GroupLayout(editProfilePanel);
		editProfilePanel.setLayout(gl);
		
		// Labels
		JLabel firstNameLabel =   new JLabel("Prénom :");
		JLabel lastNameLabel = new JLabel("Nom :");
		JLabel adressLabel =  new JLabel("Adresse :");
		JLabel zipCodeLabel = new JLabel("Code postal :");
		JLabel cityLabel =  new JLabel("Ville :");
		JLabel phoneLabel =  new JLabel("Numéro de téléphone :");
		
		// Fields		
		this.idField = new JTextField();
		this.idField.setVisible(false);
		this.firstNameField = new JTextField("");
		this.lastNameField = new JTextField("");
		this.adressField =  new JTextField("");
		this.zipCodeField = new JTextField("");
		this.cityField =  new JTextField("");
		this.phoneField =  new JTextField("");
		
		this.fieldList = new ArrayList<>();
		this.fieldList.add(firstNameField);
		this.fieldList.add(lastNameField);
		this.fieldList.add(adressField);
		this.fieldList.add(zipCodeField);
		this.fieldList.add(cityField);
		this.fieldList.add(phoneField);
		
		changeFieldEditableState(false);
		
		// Buttons
		JButton submitButton = new JButton("Modifier");
		submitButton.addActionListener(this.profileControler);
		
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
						.addGroup(gl.createParallelGroup(Alignment.LEADING)
							.addComponent(adressField)
							.addComponent(lastNameField)
							.addComponent(firstNameField)
							.addGroup(gl.createParallelGroup(Alignment.TRAILING)
								.addComponent(cityField, Alignment.LEADING)
								.addComponent(zipCodeField, Alignment.LEADING)
								.addComponent(phoneField, Alignment.LEADING)))
						.addGroup(gl.createSequentialGroup()
							.addComponent(submitButton)))));
		gl.setVerticalGroup(
			gl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstNameLabel)
						.addComponent(firstNameField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(lastNameLabel)
						.addComponent(lastNameField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(adressLabel)
						.addComponent(adressField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(zipCodeLabel)
						.addComponent(zipCodeField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(cityLabel)
						.addComponent(cityField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(phoneLabel)
						.addComponent(phoneField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(submitButton))));
		
		
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		
		// Main layout
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		this.add(loginPanel, BorderLayout.NORTH);
		this.add(editProfilePanel, BorderLayout.CENTER);
				
	}
	
	public Map<String, String> getEditedClientData() {
		Map<String, String> editedData = new HashMap<>();
		editedData.put("id", idField.getText());
		editedData.put("firstName", firstNameField.getText());
		editedData.put("lastName", lastNameField.getText());
		editedData.put("adress", adressField.getText());
		editedData.put("zipCode", zipCodeField.getText());
		editedData.put("city", cityField.getText());
		editedData.put("phone", phoneField.getText());
		
		return editedData;
	}

	public int getConnexionId() {
		try {
			return (Integer.parseInt(this.loginField.getText()));
		} catch (Exception e) {
			System.err.println("Identifiant invalide");
			e.printStackTrace();
		}	
		return 0;
	}
	
	public void clearClientField() {
		for (JTextField field : this.fieldList) {
			field.setText("");
		}
	}

	public void update(Client client) {
		firstNameField.setText(client.getFirstName());
		lastNameField.setText(client.getLastName());
		adressField.setText(client.getAdress());
		zipCodeField.setText(String.valueOf(client.getZipCode()));
		cityField.setText(client.getCity());
		phoneField.setText(String.valueOf(client.getPhone()));
		idField.setText(String.valueOf(client.getId()));
		
		
	}
	
	public void changeFieldEditableState(boolean state) {
		for (JTextField field : fieldList) {
			field.setEditable(state);
		}
	}
	
}
