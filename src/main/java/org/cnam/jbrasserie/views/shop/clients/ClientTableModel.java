package org.cnam.jbrasserie.views.shop.clients;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cnam.jbrasserie.beans.Client;
import org.cnam.jbrasserie.dao.client.ClientDao;
import org.cnam.jbrasserie.dao.client.ClientDaoImplDb;

public class ClientTableModel extends AbstractTableModel {

	List<Client> clients;

	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Numéro client", "Nom", "Prénom", "Adresse", "Code postal", "Ville", "Numéro de téléphone"};
		
	@Override
	public int getRowCount() {
		return this.clients.size();
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
			return clients.get(rowIndex).getId();
		case 1: 
			return clients.get(rowIndex).getLastName();
		case 2: 
			return clients.get(rowIndex).getFirstName();
		case 3: 
			return clients.get(rowIndex).getAdress();
		case 4: 
			return clients.get(rowIndex).getZipCode();
		case 5: 
			return clients.get(rowIndex).getCity();
		case 6:
			return clients.get(rowIndex).getPhone();
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0 :
				return Integer.class;
			case 4 :
				return Integer.class;

			default:
				return String.class;
		}
	}
	
	public void update(List<Client> clients) {
		this.clients = clients;
		fireTableDataChanged();
	}
}
