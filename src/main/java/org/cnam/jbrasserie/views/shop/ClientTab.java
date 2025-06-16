package org.cnam.jbrasserie.views.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.controlers.shop.ClientControler;
import org.cnam.jbrasserie.observers.ClientNotifier;
import org.cnam.jbrasserie.observers.ClientObserver;
import org.cnam.jbrasserie.tablesModels.ClientTableModel;

public class ClientTab extends JPanel implements ClientObserver{
	
	private static final long serialVersionUID = 1L;
	
	private ClientControler clientControler;
	
	private JPanel editPanel;
	
	private JTable clientTable;
	private ClientTableModel clientTableModel;
	
	private JTextField idField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField adressField;
	private JTextField zipCodeField;
	private JTextField cityField;
	private JTextField phoneField;
	
	JButton updateButton;
	JButton deleteButton;
	JButton newButton;
	
	JLabel message;
	
	private List<JTextField> textFieldList;
	
	public ClientTab() {
		
		clientTableModel = new ClientTableModel();
		this.clientControler = new ClientControler(this, clientTableModel);
		ClientNotifier.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		// Table

		clientTable = new JTable(clientTableModel);
		clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(clientTable);
		clientTable. setFillsViewportHeight(true);
		
		// Edit panel
		
		buildEditPanel();
		
		// Layout build
		
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(editPanel, BorderLayout.SOUTH);

		// Table listener
		
		clientTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = clientTable.getSelectedRow();
					if (selectedRow >= 0 && selectedRow < clientTableModel.getRowCount()) {
						int modelRow = clientTable.convertRowIndexToModel(selectedRow);
						Client client = clientControler.handleClientRow((int) clientTableModel.getValueAt(modelRow, 0));
						updateEditPanel(client);
						setTextFieldsEditable(true);
						setUpdateButtonState(true);
						setDeleteButtonState(true);
						changeUpdateButtonName(false);
						clearMessage();
					}
				}
			}
			
		});

	}
	
	public void updateEditPanel(Client client) {
		idField.setText(String.valueOf(client.getId()));
		firstNameField.setText(client.getFirstName());
		lastNameField.setText(client.getLastName());
		adressField.setText(client.getAdress());
		zipCodeField.setText(String.valueOf(client.getZipCode()));
		cityField.setText(client.getCity());
		phoneField.setText(String.valueOf(client.getPhone()));
	}
	
	public void clearEditPanel() {
		for (JTextField field : this.textFieldList) {
			field.setText("");
		}
	}
	
	public int getSelectedRow() {
		int selectedRow = clientTable.getSelectedRow();
		return clientTable.convertRowIndexToModel(selectedRow);
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
	
	public void setTextFieldsEditable(boolean state) {
		for (JTextField field : textFieldList) {
			field.setEditable(state);
		}
	}
	
	public void buildEditPanel() {
		
		editPanel = new JPanel();
		
		// Labels
		JLabel firstNameLabel =   new JLabel("Prénom :");
		JLabel lastNameLabel = new JLabel("Nom :");
		JLabel adressLabel =  new JLabel("Adresse :");
		JLabel zipCodeLabel = new JLabel("Code postal :");
		JLabel cityLabel =  new JLabel("Ville :");
		JLabel phoneLabel =  new JLabel("Numéro de téléphone :");
		this.message = new JLabel(" ");
		
		// Fields		
		
		idField = new JTextField();
		idField.setVisible(false);
		firstNameField = new JTextField("");
		lastNameField = new JTextField("");
		adressField =  new JTextField("");
		zipCodeField = new JTextField("");
		cityField =  new JTextField("");
		phoneField =  new JTextField("");

		textFieldList = new ArrayList<>();
		textFieldList.add(idField);
		textFieldList.add(firstNameField);
		textFieldList.add(lastNameField);
		textFieldList.add(adressField);
		textFieldList.add(zipCodeField);
		textFieldList.add(cityField);
		textFieldList.add(phoneField);
		
		// Buttons
		this.updateButton = new JButton(new AbstractAction("Valider") {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientControler.updateClient();
			}
		});
		
		this.deleteButton = new JButton(new AbstractAction("Supprimer") {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientControler.deleteClient();
			}
		});
		
		this.newButton = new JButton(new AbstractAction("Nouveau client") {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientControler.newClient();
			}
		});
		

		this.setTextFieldsEditable(false);
		this.newButton.setEnabled(true);
		this.deleteButton.setEnabled(false);
		this.updateButton.setEnabled(false);

		GroupLayout gl = new GroupLayout(editPanel);
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
							.addComponent(cityField, Alignment.LEADING)
							.addComponent(zipCodeField, Alignment.LEADING)
							.addComponent(phoneField, Alignment.LEADING))
						.addGroup(gl.createSequentialGroup()
								.addComponent(newButton)
								.addComponent(updateButton)
								.addComponent(deleteButton))
						.addComponent(message))));
		gl.setVerticalGroup(
			gl.createSequentialGroup()
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
					.addComponent(newButton)
					.addComponent(deleteButton)
					.addComponent(updateButton))
				.addGroup(gl.createParallelGroup(Alignment.BASELINE)
					.addComponent(message)));
		
		editPanel.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
	}
	
	// Buttons state 
	public void setUpdateButtonState(boolean state) {
		this.updateButton.setEnabled(state);
	}
	
	public void setDeleteButtonState(boolean state) {
		this.deleteButton.setEnabled(state);
	}
	
	public void changeUpdateButtonName(boolean isNewBeer) {
		String name = isNewBeer ? "Enregistrer" : "Mettre à jour";
		this.updateButton.setText(name);
	}
	
	// Message
	public void showError(String message) {
		this.message.setForeground(Color.RED);
		showMessage(message);
	}
	
	public void showSuccess(String message) {
		this.message.setForeground(Color.BLUE);
		showMessage(message);
	}
	
	private void showMessage(String message) {
		this.clearMessage();
		this.message.setText(message);
	}
	
	public void clearMessage() {
		this.message.setText(" ");
	}

	@Override
	public void clientUpdated() {
		this.clientControler.updateTable();
	}
}
