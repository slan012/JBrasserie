package org.cnam.jbrasserie.views.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.cnam.jbrasserie.controlers.client.ClientCatalogControler;
import org.cnam.jbrasserie.observers.CatalogNotifier;
import org.cnam.jbrasserie.observers.CatalogObserver;
import org.cnam.jbrasserie.tablesModels.ClientBeersTableModel;

@SuppressWarnings("serial")
public class ClientCatalogTab extends JPanel implements CatalogObserver{
	
	private static final long serialVersionUID = 1L;
	private JTable beerTable;
	private ClientBeersTableModel beerTableModel;
	private ClientCatalogControler catalogControler;
	
	JSpinner quantitySpinner;
	JLabel quantityLabel;
	JPanel choicePanel;
	JButton addButton;
	JLabel message;
	JPanel addToBasketPanel;
	JPanel messagePanel;
	
	public ClientCatalogTab() {
		this.setLayout(new BorderLayout());
		this.beerTableModel = new ClientBeersTableModel();
		this.catalogControler = new ClientCatalogControler(this, beerTableModel);
		CatalogNotifier.addObserver(this);
		// Beer table
		
		this.beerTable = new JTable(beerTableModel);
		this.beerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(beerTable);
		this.beerTable.setFillsViewportHeight(true);
		
	
		// Choice panel
		
		this.choicePanel = new JPanel();
		this.quantityLabel = new JLabel("Quantité :");
		this.quantitySpinner = new JSpinner(new SpinnerNumberModel(1,1, null, 1));
		this.quantitySpinner.setPreferredSize(new Dimension(60, 25));
		this.quantitySpinner.setMaximumSize(new Dimension(60, 25));

		addButton = new JButton(new AbstractAction("Ajouter au panier") {
			@Override
			public void actionPerformed(ActionEvent e) {
				catalogControler.addLineToBasket();
			}
		});

		addButton.setEnabled(false);

		// Message 
		
		this.message = new JLabel(" ");

		// Layout
		// Add To Basket panel
		
		this.addToBasketPanel = new JPanel();
		GroupLayout gl = new GroupLayout(this.addToBasketPanel);
		this.addToBasketPanel.setLayout(gl);
		JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));
		container.add(addToBasketPanel);
		gl.setHorizontalGroup(
				gl.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(gl.createSequentialGroup()
						.addComponent(this.quantityLabel)
						.addComponent(this.quantitySpinner)
						.addComponent(this.addButton))
					.addComponent(this.message));
		
		gl.setVerticalGroup(
				gl.createSequentialGroup()
					.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(quantityLabel)
						.addComponent(quantitySpinner)
						.addComponent(addButton))
					.addComponent(message));
		
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);

		this.add(scrollPane, BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		
		// Order table listener
		
		beerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				setAddButtonState(true);
				clearMessage();
			}
		});
	}
	
	public int getSelectedRow() {
		int selectedRow = beerTable.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < beerTableModel.getRowCount()) {
			int modelRow = beerTable.convertRowIndexToModel(selectedRow);
			return modelRow;
		}
		return selectedRow;
	}
	
	public int getQuantity() {
		return (int) this.quantitySpinner.getValue();
	}
	
	public void setAddButtonState(boolean state) {
		this.addButton.setEnabled(state);
	}
	
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
	public void catalogUpdated() {
		this.catalogControler.updateTable();
	}
}

