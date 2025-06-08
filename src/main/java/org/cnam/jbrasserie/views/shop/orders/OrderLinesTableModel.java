package org.cnam.jbrasserie.views.shop.orders;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.cnam.jbrasserie.beans.OrderLine;

public class OrderLinesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private String[] columnNames = {"Référence", "Nom", "Brasserie", "Style", "Alccol", "PU €", "Quantité"};
	
	private List<OrderLine> orderLines;
	
	
	@Override
	public int getRowCount() {
		return this.orderLines.size();
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
			return orderLines.get(rowIndex).getBeer().getId();
		case 1: 
			return orderLines.get(rowIndex).getBeer().getName();
		case 2: 
			return orderLines.get(rowIndex).getBeer().getBrewer();
		case 3: 
			return orderLines.get(rowIndex).getBeer().getStyle();
		case 4: 
			return orderLines.get(rowIndex).getBeer().getAlcohol();
		case 5: 
			return orderLines.get(rowIndex).getBeer().getPrice();
		case 6:
			return orderLines.get(rowIndex).getQuantity();
		
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
	
	public void update(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
		fireTableDataChanged();
	}
	
	public void init() {
		this.orderLines = new ArrayList<>();
	}
}
