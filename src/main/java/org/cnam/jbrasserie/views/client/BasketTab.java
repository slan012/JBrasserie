package org.cnam.jbrasserie.views.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.cnam.jbrasserie.controlers.client.BasketControler;
import org.cnam.jbrasserie.tablesModels.OrderLinesTableModel;

@SuppressWarnings("serial")
public class BasketTab extends JPanel {
	
	private JTable basketTable;
	private OrderLinesTableModel basketTableModel;
	private BasketControler basketControler;
	
	JButton deleteButton;
	AbstractButton submitButton;
	JButton emptyButton;
	
	public BasketTab() {
		
		basketTableModel = new OrderLinesTableModel();
		basketTableModel.init();
		basketTable = new JTable(basketTableModel);
		basketControler = new BasketControler(this, basketTableModel);
		
		// Table
		
		basketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane basketScrollPane = new JScrollPane(basketTable);
		basketTable. setFillsViewportHeight(true);
		
		// Button panel
		
		JPanel buttonsPanel = new JPanel();
		
		// Buttons
		 this.submitButton = new JButton(new AbstractAction("Confirmer la commande") {
			public void actionPerformed(ActionEvent e) {
				 basketControler.confirmCommand();
			 }
		 });
		 
		 this.deleteButton = new JButton(new AbstractAction("Supprimer article") {
				public void actionPerformed(ActionEvent e) {
					 basketControler.deleteLine();
				 }
			 });
		 this.emptyButton = new JButton(new AbstractAction("Vider le panier") {
				public void actionPerformed(ActionEvent e) {
					 basketControler.emptyBasket();
				 }
			 });
		 
		 this.setButtonsState(false);
		
		// Layout
		
		buttonsPanel.add(submitButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(emptyButton);
		 
		this.setLayout(new BorderLayout());
		
		this.add(basketScrollPane, BorderLayout.NORTH);
		this.add(buttonsPanel, BorderLayout.CENTER);
	}
	
	public int getSelectedRow() {
		int selectedRow = basketTable.getSelectedRow();
		if (selectedRow >= 0 && selectedRow < basketTableModel.getRowCount()) {
			return basketTable.convertRowIndexToModel(selectedRow);
		}
		return selectedRow;
	}
	
	public void setButtonsState(boolean state) {
		this.submitButton.setEnabled(state);
		this.deleteButton.setEnabled(state);
		this.emptyButton.setEnabled(state);
	}
}
