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
import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.controlers.shop.CatalogControler;
import org.cnam.jbrasserie.tablesModels.BeersTableModel;

@SuppressWarnings("serial")
public class CatalogTab extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel editPanel;
	private JTable beerTable;
	private BeersTableModel beerTableModel;
	private CatalogControler catalogControler;
	
	private JTextField idField;
	private JTextField nameField;
	private JTextField brewerField;
	private JTextField styleField;
	private JTextField alcoholField;
	private JTextField priceField;
	private JTextField stockField;
	
	JButton updateButton;
	JButton deleteButton;
	JButton newEntryButton;
	
	JLabel message;
	
	private List<JTextField> textFieldList;
	
	public CatalogTab() {
		
		this.setLayout(new BorderLayout());
		beerTableModel = new BeersTableModel();
		this.catalogControler = new CatalogControler(this, beerTableModel);
		buildEditPanel();
		
		// Beer table
		
		beerTable = new JTable(beerTableModel);
		beerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(beerTable);
		beerTable.setFillsViewportHeight(true);

		this.add(scrollPane, BorderLayout.CENTER);
		this.add(editPanel, BorderLayout.SOUTH);
		
		// Table listener
		
		beerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = beerTable.getSelectedRow();
					if (selectedRow >= 0 && selectedRow < beerTableModel.getRowCount()) {
						int modelRow = beerTable.convertRowIndexToModel(selectedRow);
						Beer beer = catalogControler.handleBeerRow((int) beerTableModel.getValueAt(modelRow, 0));
						updateEditPanel(beer);
						setTextFieldsState(true);
						changeUpdateButtonName(false);
						setUpdateButtonState(true);
						setDeleteButtonState(true);
						clearMessage();
					}
				}
			}
		});
	}
	
	public void updateEditPanel(Beer beer) {
		idField.setText(String.valueOf(beer.getId()));
		nameField.setText(beer.getName());
		brewerField.setText(beer.getBrewer());
		styleField.setText(beer.getStyle());
		alcoholField.setText(String.valueOf(beer.getAlcohol()));
		priceField.setText(String.valueOf(beer.getPrice()));
		stockField.setText(String.valueOf(beer.getStock()));
	}
	
	public void clearEditPanel() {
		for (JTextField field : this.textFieldList) {
			field.setText("");
		}
	}
	
	public int getSelectedRow() {
		int selectedRow = this.beerTable.getSelectedRow();
		return this.beerTable.convertRowIndexToModel(selectedRow);
	}
	
	public Map<String, String> getEditedBeerData() {
		Map<String, String> editedData = new HashMap<>();
		editedData.put("id", idField.getText());
		editedData.put("name", nameField.getText());
		editedData.put("brewer", brewerField.getText());
		editedData.put("style", styleField.getText());
		editedData.put("alcohol", alcoholField.getText());
		editedData.put("price", priceField.getText());
		editedData.put("stock", stockField.getText());
		
		return editedData;
	}
	
	public void setTextFieldsState(boolean state) {
		for (JTextField field : textFieldList) {
			field.setEditable(state);
		}
	}
	
	public void changeUpdateButtonName(boolean isNewBeer) {
		String name = isNewBeer ? "Enregistrer" : "Mettre à jour";
		this.updateButton.setText(name);
	}
	
	// Buttons state 
	public void setUpdateButtonState(boolean state) {
		this.updateButton.setEnabled(state);
	}
	public void setDeleteButtonState(boolean state) {
		this.deleteButton.setEnabled(state);
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

	public void buildEditPanel() {
		
		this.editPanel = new JPanel();
		
		// Labels
		JLabel nameLabel =    new JLabel("Nom :");
		JLabel brewerLabel =  new JLabel("Brasseur :");
		JLabel styleLabel  =  new JLabel("Style :");
		JLabel alcoholLabel = new JLabel("Alcool :");
		JLabel priceLabel =   new JLabel("Prix :");
		JLabel stockLabel =   new JLabel("Stock :");
		this.message =        new JLabel(" ");
		// Fields		
		
		this.idField =      new JTextField();
		this.nameField =    new JTextField();
		this.brewerField =  new JTextField();
		this.styleField =   new JTextField();
		this.alcoholField = new JTextField();
		this.priceField =   new JTextField();
		this.stockField =   new JTextField();
		this.idField.setVisible(false);
		
		this.textFieldList = new ArrayList<>();
		this.textFieldList.add(this.idField);
		this.textFieldList.add(this.nameField);
		this.textFieldList.add(this.brewerField);
		this.textFieldList.add(this.styleField);
		this.textFieldList.add(this.alcoholField);
		this.textFieldList.add(this.priceField);
		this.textFieldList.add(this.stockField);
		
		// Button "Ajouter" / "Mettre à jour"
		updateButton = new JButton(new AbstractAction("Mettre à jour") {
			@Override
			public void actionPerformed(ActionEvent e) {
				catalogControler.updateChanges();			
			}
		});
		
		deleteButton = new JButton(new AbstractAction("Supprimer") {
			@Override
			public void actionPerformed(ActionEvent e) {
				catalogControler.deleteEntry();			
			}
		});
		
		newEntryButton = new JButton(new AbstractAction("Nouvel article") {
			@Override
			public void actionPerformed(ActionEvent e) {
				catalogControler.newEntry();			
			}
		});
		
		setTextFieldsState(false);
		setUpdateButtonState(false);
		setDeleteButtonState(false);

		GroupLayout gl = new GroupLayout(editPanel);
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addComponent(nameLabel)
						.addComponent(brewerLabel)
						.addComponent(styleLabel)
						.addComponent(alcoholLabel)
						.addComponent(priceLabel)
						.addComponent(stockLabel))
					.addGroup(gl.createParallelGroup(Alignment.LEADING)
						.addGroup(gl.createParallelGroup(Alignment.LEADING)
							.addComponent(styleField)
							.addComponent(brewerField)
							.addComponent(nameField)
							.addComponent(priceField, Alignment.LEADING)
							.addComponent(alcoholField, Alignment.LEADING)
							.addComponent(stockField, Alignment.LEADING))
						.addGroup(gl.createSequentialGroup()
							.addComponent(newEntryButton)
							.addComponent(updateButton)
							.addComponent(deleteButton))
						.addComponent(message)));
		gl.setVerticalGroup(
			gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(nameField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(brewerLabel)
						.addComponent(brewerField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(styleLabel)
						.addComponent(styleField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(alcoholLabel)
						.addComponent(alcoholField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(priceLabel)
						.addComponent(priceField))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(stockLabel)
						.addComponent(stockField))

					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(newEntryButton)
						.addComponent(updateButton)
						.addComponent(deleteButton))
					.addGroup(gl.createParallelGroup(Alignment.BASELINE)
						.addComponent(message)));
		
		editPanel.setLayout(gl);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
	}
}
