package org.cnam.jbrasserie.views.shop.clients;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.controlers.shop.ClientControler;


public class ClientTab extends JPanel{
	
	/**
	 * 
	 */
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
	
	private List<JTextField> textFieldList;
	
	public ClientTab() {
		
		clientTableModel = new ClientTableModel();
		this.clientControler = new ClientControler(this, clientTableModel);
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
	
	public void buildEditPanel() {
		
		editPanel = new JPanel();
		
		// Labels
		JLabel firstNameLabel =   new JLabel("Prénom :");
		JLabel lastNameLabel = new JLabel("Nom :");
		JLabel adressLabel =  new JLabel("Adresse :");
		JLabel zipCodeLabel = new JLabel("Code postal :");
		JLabel cityLabel =  new JLabel("Ville :");
		JLabel phoneLabel =  new JLabel("Numéro de téléphone :");
		
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
		JButton submitButton = new JButton("Valider");
		JButton deleteButton = new JButton("Supprimer");
		JButton newButton =    new JButton("Nouveau");
		
		submitButton.addActionListener(this.clientControler);
		deleteButton.addActionListener(this.clientControler);
		newButton.addActionListener(this.clientControler);
		

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
							.addGroup(gl.createParallelGroup(Alignment.TRAILING)
								.addComponent(cityField, Alignment.LEADING)
								.addComponent(zipCodeField, Alignment.LEADING)
								.addComponent(phoneField, Alignment.LEADING)))
						.addGroup(gl.createSequentialGroup()
							.addComponent(submitButton)
							.addComponent(deleteButton)
							.addComponent(newButton)))));
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
						.addComponent(submitButton)
						.addComponent(deleteButton)
						.addComponent(newButton))));
		
		editPanel.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);

	}
}
