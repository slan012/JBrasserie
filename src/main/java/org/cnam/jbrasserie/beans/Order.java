package org.cnam.jbrasserie.beans;

import java.util.Date;

public class Order {
	private Integer idOrder; //AUTO INCREMENT
	private Integer idClient;
	private Date date;
	private double total;
	


	public Integer getIdOrder() {
		return idOrder;
	}
	
	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}
	
	public Integer getIdClient() {
		return idClient;
	}
	
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
