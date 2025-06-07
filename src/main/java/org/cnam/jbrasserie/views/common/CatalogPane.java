package org.cnam.jbrasserie.views.common;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class CatalogPane extends JPanel{

	private static final long serialVersionUID = 1L;

	public CatalogPane() {
		setLayout(new BorderLayout());
		BeersTableModel model = new BeersTableModel();
		
		// Beer table
		
		JTable beerTable = new JTable(model);
		beerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(beerTable);
		beerTable.setFillsViewportHeight(true);
		this.add(scrollPane, BorderLayout.CENTER);
	}
}
