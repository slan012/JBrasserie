package org.cnam.jbrasserie.tablesModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cnam.jbrasserie.beans.Order;

public class OrderTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Référence commande", "Référence client", "Nom", "Prénom", "Ville", "Téléphone", "Montant"};
	private List<Order> orders;
	
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
		switch (columnIndex) {
		case 0: 
			return orders.get(rowIndex).getIdOrder();
		case 1: 
			return orders.get(rowIndex).getClient().getId();
		case 2: 
			return orders.get(rowIndex).getClient().getLastName();
		case 3: 
			return orders.get(rowIndex).getClient().getFirstName();
		case 4: 
			return orders.get(rowIndex).getClient().getCity();
		case 5: 
			return orders.get(rowIndex).getClient().getPhone();
		case 6:
			return orders.get(rowIndex).getTotal();
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0 :
				return Integer.class;
			case 1 :
				return Integer.class;
			case 6 :
				return Float.class;
			default:
				return String.class;
		}
	}
	
	public void update(List<Order> orders) {
		this.orders = orders;
		fireTableDataChanged();
	}
}
