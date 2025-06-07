package org.cnam.jbrasserie.views.shop.orders;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.beans.Order;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;
import org.cnam.jbrasserie.dao.order.OrderDao;
import org.cnam.jbrasserie.dao.order.OrderDaoImplDb;

public class OrderTableModel extends AbstractTableModel{
	
	
	OrderDao orderDao = new OrderDaoImplDb();

	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Numéro commande", "Date", "Nom", "Prénom", "Numéro de téléphone", "Ville", "Montant"};
	private List<Order> orders = orderDao.findAll();
	
	
	@Override
	public int getRowCount() {
		return this.orders.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return columnIndex;
		
		

	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0 :
				return Integer.class;
			case 4 :
				return Integer.class;
			case 6 :
				return Integer.class;
			default:
				return String.class;
		}
	}
}
