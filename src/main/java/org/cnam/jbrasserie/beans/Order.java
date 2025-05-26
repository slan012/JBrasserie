package org.cnam.jbrasserie.beans;

import java.util.Date;

public class Order {
	private Integer idOrder; //AUTO INCREMENT
	private Integer idClient;
	private Date date;
	private int total;
	
	public Integer getIdOrder() {
		return idOrder;
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

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public void setTotal(double double1) {
		// TODO Auto-generated method stub
		
	}
}
