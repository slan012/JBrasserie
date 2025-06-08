package org.cnam.jbrasserie.views.shop.orders;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.cnam.jbrasserie.beans.OrderLine;
import org.cnam.jbrasserie.controlers.shop.OrderControler;

public class OrderTab extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTable orderTable;
	private OrderTableModel orderTableModel;
	private OrderLinesTableModel orderLinesTableModel;
	private OrderControler orderControler;


	private JTable orderLinesTable;
	
	
	public OrderTab() {
		

		orderTableModel = new OrderTableModel();
		orderLinesTableModel = new OrderLinesTableModel();
		
		this.orderControler = new OrderControler(this, orderTableModel, orderLinesTableModel);
		
		// Order table
		
		orderTable = new JTable(orderTableModel);
		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane catalogPane = new JScrollPane(orderTable);

		orderTable.setFillsViewportHeight(true);

		
		// Order table listener
		
		orderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = orderTable.getSelectedRow();
					if (selectedRow >= 0 && selectedRow < orderTableModel.getRowCount()) {
						int modelRow = orderTable.convertRowIndexToModel(selectedRow);
						System.out.print(orderTableModel.getValueAt(modelRow, 0));
						List <OrderLine> orderLines = orderControler.getOrderLines((int) orderTableModel.getValueAt(modelRow, 0));
						orderLinesTableModel.update(orderLines);
					}
				}
			}
		});
		
		// Order lines tables
		orderLinesTable = new JTable(orderLinesTableModel);
		orderLinesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane linesScrollPane = new JScrollPane(orderLinesTable);
		orderLinesTable.setFillsViewportHeight(true);

		// Main Layout 
		
		BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(bl);
		this.add(catalogPane);
		
		JPanel linesTab = new JPanel();
		linesTab.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(),
						"Détails de la commande",
						TitledBorder.CENTER,
						TitledBorder.TOP));
		
		BoxLayout bl2 = new BoxLayout(linesTab, BoxLayout.Y_AXIS);
		linesTab.setLayout(bl2);
		linesTab.add(linesScrollPane);
		this.add(linesTab);
	}
}
