package org.cnam.jbrasserie.views.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
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
import org.cnam.jbrasserie.tablesModels.ClientBeersTableModel;

@SuppressWarnings("serial")
public class ClientCatalogTab extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTable beerTable;
	private ClientBeersTableModel beerTableModel;
	private ClientCatalogControler catalogControler;
	
	JSpinner quantitySpinner;
	JLabel quantityLabel;
	JButton addButton;
	
	public ClientCatalogTab() {
		this.setLayout(new BorderLayout());
		beerTableModel = new ClientBeersTableModel();
		this.catalogControler = new ClientCatalogControler(this, beerTableModel);
		
		// Beer table
		
		beerTable = new JTable(beerTableModel);
		beerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(beerTable);
		beerTable.setFillsViewportHeight(true);
		
		// Add To Basket panel
		
		JPanel addToBasketPanel = new JPanel();
		
		// Text Field
		
		this.quantityLabel = new JLabel("Quantité :");
		this.quantitySpinner = new JSpinner(new SpinnerNumberModel(1,1, null, 1));
		
		// Button
		
		addButton = new JButton(new AbstractAction("Ajouter au panier") {
			@Override
			public void actionPerformed(ActionEvent e) {
				catalogControler.addLineToBasket();
			}
		});

		addButton.setEnabled(false);
		
		// Layout
		
		addToBasketPanel.add(quantityLabel);
		addToBasketPanel.add(quantitySpinner);
		addToBasketPanel.add(addButton);

		this.add(scrollPane, BorderLayout.NORTH);
		this.add(addToBasketPanel, BorderLayout.CENTER);
		
		// Order table listener
		
		beerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				setAddButtonState(true);
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
}

