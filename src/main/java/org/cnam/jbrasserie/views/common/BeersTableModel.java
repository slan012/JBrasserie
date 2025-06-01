package org.cnam.jbrasserie.views.common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cnam.jbrasserie.beans.Beer;
import org.cnam.jbrasserie.dao.beer.BeerDao;
import org.cnam.jbrasserie.dao.beer.BeerDaoImplDb;

public class BeersTableModel extends AbstractTableModel{

	
	BeerDao beerDao = new BeerDaoImplDb();

	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Référence", "Nom", "Brasserie", "Style", "Alcool", "PU €", "Stock"};
	private List<Beer> beers = beerDao.findAll();
	
	
	@Override
	public int getRowCount() {
		return this.beers.size();
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
			return beers.get(rowIndex).getId();
		case 1: 
			return beers.get(rowIndex).getName();
		case 2: 
			return beers.get(rowIndex).getBrewer();
		case 3: 
			return beers.get(rowIndex).getStyle();
		case 4: 
			return beers.get(rowIndex).getAlcohol();
		case 5: 
			return beers.get(rowIndex).getPrice();
		case 6:
			return beers.get(rowIndex).getStock();
		
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
				return Float.class;
			case 5 :
				return Float.class;	
			case 6 :
				return Integer.class;
			default:
				return String.class;
		}
	}
}
