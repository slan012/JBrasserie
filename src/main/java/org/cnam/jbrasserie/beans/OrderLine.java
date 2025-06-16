package org.cnam.jbrasserie.beans;

import org.cnam.jbrasserie.exceptions.BeanException;

public class OrderLine {
	private Integer idOrder;
	private Beer beer;
	private Integer quantity;
	
	public Integer getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public Beer getBeer() {
		return beer;
	}
	
	public void setBeer(Beer beer) {
		this.beer = beer;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) throws BeanException {
		if (this.beer.getStock() >= quantity) {
			this.quantity = quantity;
		} else {
			throw new BeanException("Stock insuffisant !");
		}
	}
}