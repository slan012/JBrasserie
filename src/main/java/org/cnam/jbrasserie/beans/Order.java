package org.cnam.jbrasserie.beans;

import java.util.Date;

public class Order {
	private Integer idOrder; //AUTO INCREMENT
	private Integer idClient;
	private Date date;
	
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
}
