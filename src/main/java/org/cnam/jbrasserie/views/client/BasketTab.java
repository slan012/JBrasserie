package org.cnam.jbrasserie.views.client;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import org.cnam.jbrasserie.controlers.client.BasketControler;
import org.cnam.jbrasserie.tablesModels.OrderLinesTableModel;

public class BasketTab extends JPanel {
	
	private JTable basketTable;
	private OrderLinesTableModel basketTableModel;
	private BasketControler basketControler;
	
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
		 JButton submitButton = new JButton("Valider la commande");
		 JButton deleteButton = new JButton("Supprimer");
		 JButton emptyButton = new JButton("Vider le panier");
		 
		 submitButton.addActionListener(basketControler);
		 deleteButton.addActionListener(basketControler);
		 emptyButton.addActionListener(basketControler);
		
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
}
