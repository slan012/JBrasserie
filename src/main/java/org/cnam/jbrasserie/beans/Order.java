package org.cnam.jbrasserie.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private Integer idOrder; //AUTO INCREMENT
	private Client client;
	private Date date;
	private List<OrderLine> lines;
	private Double total;
	private PropertyChangeSupport support;
	
	public Order() {
		lines = new ArrayList<>();
		this.support = new PropertyChangeSupport(this);
	}
	
	public Integer getIdOrder() {
		return idOrder;
	}
	
	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<OrderLine> getLines() {
		return lines;
	}
	
	public void setLines(List<OrderLine> lines) {
		List<OrderLine> oldList = new ArrayList<>(this.lines);
		this.lines = lines;
		this.support.firePropertyChange("lines", oldList, lines);
	}
	
	public void addLine(OrderLine line) {
		List<OrderLine> oldList = new ArrayList<>(this.lines);
		lines.add(line);

		this.support.firePropertyChange("lines", oldList, lines);
	}
	
	public void removeLine(int lineIndex) {
		List<OrderLine> oldList = new ArrayList<>(this.lines);
		lines.remove(lineIndex);
		this.support.firePropertyChange("lines", oldList, lines);
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(listener);
	}
}
